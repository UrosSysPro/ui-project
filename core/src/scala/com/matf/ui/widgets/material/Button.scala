package com.matf.ui.widgets.material

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.Colors
import com.matf.ui.widgets.{Container, GestureDetector, SizedBox, State, StatefulWidget, StatelessWidget}

class Button(child:Widget=null,val onClick:()=>Unit) extends StatefulWidget{
  override def createState(): State = new ButtonState()
}

class ButtonState extends State{
  override def build(context: BuildContext): Widget = {
    var mouseDownTime: Int = 0
    var mouseUpTime: Int = 0

    Container(
      color = Colors.red500,
      child = SizedBox(
        width = 50,
        height = 50,
        child = GestureDetector(
          mouseDown = (_, _, _, _) => {
            mouseDownTime = System.currentTimeMillis().toInt
            true
          },
          mouseUp = (_, _, _, _) => {
            mouseUpTime = System.currentTimeMillis().toInt
            if ((mouseDownTime - mouseUpTime) < 300){
              val onClick=widget match {
                case button: Button=>button.onClick
              }
            }
            true
          }
        )
      )
    )
  }
}

object Button{
  def apply(child:Widget=null,onClick:()=>Unit): Button = new Button(child, onClick)
}