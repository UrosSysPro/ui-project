package com.matf.ui.widgets

import com.matf.ui.Widget
import com.matf.ui.utils.context.BuildContext

class Builder(builder:()=>Widget) extends StatelessWidget {
  override def build(context:BuildContext): Widget = builder()
}

object Builder{
  def apply(build: () => Widget=()=>null): Builder = new Builder(build)
}
