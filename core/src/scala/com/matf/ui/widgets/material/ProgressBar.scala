package com.matf.ui.widgets.material

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.widgets.{Container, SizedBox, StatelessWidget}
import org.joml.{Vector2f, Vector4f}

class ProgressBar(val value:Float) extends StatelessWidget {
  override def build(context: BuildContext): Widget = {
    SizedBox(
      width=100,height=5,
      child = SizedBox(
        width=100*value,height=5,
        child = Container(color = new Vector4f(0.2f,0.4f,0.9f,1.0f))
      )
    )
  }
}

object ProgressBar{
  def apply(value: Float): ProgressBar = new ProgressBar(value)
}
