package com.matf.ui.widgets.material

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.CrossAxisAlignment.center
import com.matf.ui.utils.data.{BoxDecoration, BoxShadow, MainAxisAlignment}
import com.matf.ui.utils.data.MainAxisAlignment.{spaceAround, spaceBetween}
import com.matf.ui.widgets.{Container, Row, SizedBox, StatelessWidget, Text}
import org.joml.{Vector2f, Vector4f}

import scala.collection.mutable.ArrayBuffer

class AppBar(val leading:Widget=null, val textValue:String="", val action:Widget=null) extends StatelessWidget{
  override def build(context: BuildContext): Widget = {
    var a : Array[Widget] = Array[Widget]()
    if(leading != null)
      a :+= leading
    if(textValue != "")
      a :+= Text(text = textValue)
    if(action != null)
      a :+= action

    Container(
      decoration = BoxDecoration(
        boxShadow = Array(BoxShadow(
          offset = new Vector2f(0,0),
          size = 1,
          blur = 1,
          color =new Vector4f(0.5f,0.5f,0.5f,1f)
        ))
      ),
      color = new Vector4f(0.2f,0.4f,0.9f,1.0f),
      child = SizedBox(
        height = 70,
        child = Row(
          crossAxisAlignment = center,
          mainAxisAlignment = spaceBetween,
          children = a
        )
      )
    )
  }
}

object AppBar{
  def apply(leading:Widget=null,textValue: String="", action:Widget=null): AppBar = new AppBar(leading, textValue, action)
}

