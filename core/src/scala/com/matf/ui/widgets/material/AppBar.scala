package com.matf.ui.widgets.material

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{Alignment, AxisSize, BoxDecoration, BoxShadow, Colors}
import com.matf.ui.widgets.{Align, Container, Expanded, Row, SizedBox, StatelessWidget}
import org.joml.{Vector2f, Vector4f}

class AppBar(
              leading:Widget,
              title:Widget,
              actions:Array[Widget],
              color:Vector4f,
              shadow:BoxShadow,
              height:Float
            ) extends StatelessWidget{
  override def build(context: BuildContext): Widget = {
    Container(
      decoration = BoxDecoration(
        color=color,
        boxShadow = Array(shadow)
      ),
      child=SizedBox(
        height=height,
        child=Row(
          mainAxisSize=AxisSize.expand,
          children = (Array(leading,Expanded(
            child=Align(
              alignment = Alignment.centerRight,
              child=title
            )
          ))++actions).filter(p=>p!=null)
        )
      )
    )
  }
}

object AppBar{
  def apply(leading: Widget=null,
            title: Widget=null,
            actions: Array[Widget]=Array(),
            color: Vector4f=Colors.blue500,
            shadow: BoxShadow=BoxShadow(
              offset = new Vector2f(0,5), blur = 10, color = new Vector4f(0,0,0,0.3f)
            ),
            height: Float=70
           ): AppBar = new AppBar(leading, title, actions, color, shadow, height)
}
