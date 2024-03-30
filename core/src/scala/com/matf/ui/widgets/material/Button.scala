package com.matf.ui.widgets.material

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{BoxDecoration, Colors}
import com.matf.ui.widgets.{Container, EdgeInsets, GestureDetector, Padding, SizedBox, State, StatefulWidget, StatelessWidget}

class Button(val widget:Widget=null, val onClick:()=>Unit) extends StatefulWidget{
  override def createState(): State = new ButtonState()
}

class ButtonState extends State{
  override def build(context: BuildContext): Widget = {
    var mouseDownTime: Int = 0
    var mouseUpTime: Int = 0

    Container(
      decoration=BoxDecoration(
        color=Colors.red500,
        borderRadius=10
      ),
      child = Padding(
        padding=EdgeInsets.symetric(horizontal = 20,vertical = 10),
        child = GestureDetector(
          mouseDown = (_, _, _, _) => {
            mouseDownTime = System.currentTimeMillis().toInt
            true
          },
          mouseUp = (_, _, _, _) => {
            mouseUpTime = System.currentTimeMillis().toInt
            if ((mouseDownTime - mouseUpTime) < 300)widget match {
              case button: Button => button.onClick()
            }

            true
          },
          child=widget match {
            case button: Button=>button.widget
          }
        )
      )
    )
  }
}

object Button{
  def apply(child:Widget=null,onClick:()=>Unit): Button = new Button(child, onClick)
}