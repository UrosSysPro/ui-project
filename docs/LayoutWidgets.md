# Layout widgets
These are invisible widgets that organize things on screen.

## Row Column Stack
These all accept parameter `children:Array[Widget]`. `Row` puts all child widgets on x axis, `Column` on y axis, `Stack` on z axis(one on top of the other).
Without these we would only be able to create a tree of widgets where every node only has one child element.
`Row` and `Column` also accept `mainAxisSize` and `crossAxisSize` that define weather these widgets fill maximum space or is their size dependent on total size of all child widgets.
They also accept `mainAxisAlignment` and `crossAxisAlignment` that define how widgets are aligned along respective axis.
### Expanded and Positioned
`Expanded` is a widget that has a special property when it is a direct child of `Row` or `Column` widget, it fills all free space on the main axis.<br>
`Positioned` is a widget that has a special property when it is a direct child of `Stack` widget, it accepts `top`, `bottom`, `left` and `right`, if none are 
supplied widget will fill the whole stack, if any value is supplied edges of Stack and Positioned will be at the exact distance. This can be useful when
we want to have an image and in the bottom right corner something else over the image.

## Align and Center

`Align` fills the whole parent and accepts `alignment:Alignment` enum that tells it in which corner to put child widget.
`Center` is a function that returns Align with default parameter `Alignment.center`