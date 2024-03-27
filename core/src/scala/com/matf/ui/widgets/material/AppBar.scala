package com.matf.ui.widgets.material

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.CrossAxisAlignment.center
import com.matf.ui.utils.data.MainAxisAlignment
import com.matf.ui.utils.data.MainAxisAlignment.spaceAround
import com.matf.ui.widgets.{Container, Row, SizedBox, StatelessWidget, Text}
import org.joml.Vector4f

class AppBar(val textValue:String="") extends StatelessWidget{
  override def build(context: BuildContext): Widget = {
    Container(
      color = new Vector4f(0.2f,0.4f,0.9f,1.0f),
      child = SizedBox(
        height = 70,
        child = Row(
          crossAxisAlignment = center,
          mainAxisAlignment = spaceAround,
          children = Array(Text(text = textValue))
        )
      )
    )
  }
}

object AppBar{
  def apply(textValue: String): AppBar = new AppBar(textValue)
}

