package net.ofd.oflib.event;


import java.lang.reflect.Method;

public class EventPublisher
{

	public static void raiseEvent ( final Event event )
	{
		new Thread()
		{
			@Override
			public void run ()
			{
				raise( event );
			}
		}.start();
	}

	public static void raise ( final Event event )
	{
		for ( Class handler : HandlerRegistry.getHandlers() )
		{
			Method[] methods = handler.getMethods();

			for ( int i = 0; i < methods.length; ++ i )
			{
				SubscribeEvent eventHandler = methods[ i ].getAnnotation( SubscribeEvent.class );
				if ( eventHandler != null )
				{
					Class[] methodParams = methods[ i ].getParameterTypes();

					if ( methodParams.length < 1 )
					{
						continue;
					}

					if ( ! event.getClass().getSimpleName()
							.equals( methodParams[ 0 ].getSimpleName() ) )
					{
						continue;
					}

					// defence from runtime exceptions:
					try
					{
						methods[ i ].invoke( handler.newInstance(), event );
					}
					catch ( Exception e )
					{
						e.printStackTrace();
						//System.err.println( e );
					}
				}
			}
		}
	}
}