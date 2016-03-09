/*
 * Copyright (c) 2016.
 * This file is part of OFlib.
 *
 * OFlib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OFlib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OFlib.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.OnyxFoxDevelopment.cli;

import com.github.OnyxFoxDevelopment.environment.Environment;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

public class Console
{
	private static java.io.Console console = null;

	public Console()
	{
		this(System.console());
	}

	public Console(java.io.Console console)
	{
		Console.console = console;
	}

	public static void print(Object o)
	{
		print(o, true);
	}

	public static void print(Object o, boolean newline)
	{
		if (console == null)
		{
			console = System.console();
		}
		console.printf("%s" + (newline ? "%n" : ""), String.valueOf(o));
	}

	public static class Prompt
	{
		public static String basic(String fmt, Object... args)
		{
			return console.readLine(fmt, args);
		}

		public static char[] password(String fmt, Object... args)
		{
			return console.readPassword(fmt, args);
		}

		public static int menu(@Nullable String title, @Nonnull String... options)
		{
			if (console == null)
			{
				console = System.console();
			}
			if (!Environment.isClassLoaded("com.googlecode.lanterna.terminal.Terminal"))
			{
				if (title != null && !title.isEmpty())
				{
					console.printf("%s%n", title);
				}
			}
			else
			{
				try
				{
					Terminal terminal = new DefaultTerminalFactory().setSuppressSwingTerminalFrame(true).createTerminal();
					Screen screen = new TerminalScreen(terminal);
					screen.startScreen();
					Panel panel = new Panel();
					panel.setLayoutManager(new GridLayout(2));

					//panel.addComponent(new Label("Forename"));
					//panel.addComponent(new TextBox());

					//panel.addComponent(new Label("Surname"));
					//panel.addComponent(new TextBox());

					//panel.addComponent(new EmptySpace(new TerminalSize(0,0))); // Empty space underneath labels
					//panel.addComponent(new Button("Submit"));

					ActionListBox alb = new ActionListBox();
					alb.addItem("aaa", new Runnable()
					{
						@Override
						public void run()
						{
							String user = Prompt.basic("Username: ");
							String pass = String.valueOf(Prompt.password("Password: "));
							Console.print("User: " + user);
							Console.print("Pass: " + pass);
						}
					});
					alb.addTo(panel);
					// Create window to hold the panel
					BasicWindow window = new BasicWindow();
					window.setComponent(panel);

					// Create gui and start gui
					MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
					gui.addWindowAndWait(window);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			return 0;
		}
	}
}
