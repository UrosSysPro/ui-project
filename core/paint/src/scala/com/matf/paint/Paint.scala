package com.matf.paint

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.Colors
import com.matf.ui.widgets.{Container, Expanded, Row, SizedBox, State, StatefulWidget}

class Paint extends StatefulWidget{
  override def createState(): State = new PaintState()
}
class PaintState extends State{
  val width=32
  val heigth=32
  var painting:Array[Array[Int]]=(0 until width).map{ i =>
    (0 until heigth).map{ j =>
      0
    }.toArray
  }.toArray

  override def build(context: BuildContext): Widget = {
    Container(
      color = Colors.green600,
      child = Row(
        children = Array(
          Container(
            color = Colors.green300,
            child = SizedBox(
              width = 300
            )
          ),
          Expanded(
            child = Row(
              children = Array()
            )
          )
        )
      )
    )
  }
}
