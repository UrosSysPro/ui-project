package com.matf.paint

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.Colors
import com.matf.ui.widgets.{Container, Expanded, Row, SizedBox, State, StatefulWidget}

class Paint extends StatefulWidget{
  override def createState(): State = new PaintState()
}
class PaintState extends State{
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
