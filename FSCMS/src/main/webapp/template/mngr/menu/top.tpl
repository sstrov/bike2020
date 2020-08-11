<!--@1depth--><ul class="nav">
	<!--@1depthList-->
	<li class="<!--@childCls-->has-sub<!--@childCls--> <!--@active-->active<!--@active-->">

		<a href="{link}" target="{target}">
			<!--@child--><b class="caret pull-right"></b><!--@child-->
			<span>{name}</span>
		</a>
		<!--@2depth-->
		<ul class="sub-menu">
			<!--@2depthList-->
			<li class="<!--@childCls-->has-sub<!--@childCls--> <!--@active-->active<!--@active-->">
				<a href="{link}" target="{target}">
					<!--@child--><b class="caret pull-right"></b><!--@child-->
					{name}
				</a>
				<!--@3depth-->
				<ul class="sub-menu">
					<!--@3depthList-->
					<li <!--@active-->class="active"<!--@active-->>
						<a href="{link}" target="{target}">
							{name}
						</a>
					</li>
					<!--@3depthList-->
				</ul>
				<!--@3depth-->
			</li>
			<!--@2depthList-->
		</ul>
		<!--@2depth-->
	</li>
	<!--@1depthList-->
</ul><!--@1depth-->