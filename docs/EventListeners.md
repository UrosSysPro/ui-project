# Event Listeners
In Opengl `Window` defines a few callbacks for handling events such as window resize, keyboard, mouse...
These callbacks are executed once per frame per window.
`runApp` creates a window and registers default callbacks for all event that do the following:
1. when an event is detected dfs algorithm is run to find all `GestureDetector` widgets affected by event
2. Each `GestureDetector` widget has its own callback for all events, these methods return boolean weather this widget accepted the event
3. Widgets callbacks are executed until first time true is returned, that way only top most widget will accept event, and events wont go through widgets
