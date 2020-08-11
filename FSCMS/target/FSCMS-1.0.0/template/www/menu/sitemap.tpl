<!--@1depth--><div id="sitemap">
	<div class="site_map_col clearfix">
		<!--@1depthList-->
		<div class="sitemap_box">
			<h2><a href="<c:url value='{link}' />" target="{target}">{name}</a></h2>
			<!--@2depth-->
			<ul>
				<!--@2depthList-->
				<li><a href="<c:url value='{link}' />" target="{target}" class="sm_2th">{name}</a>
					<!--@3depth-->
					<ul>
						<!--@3depthList-->
						<li><a href="<c:url value='{link}' />" target="{target}" class="sm_3th"><span>{name}</span></a></li>
						<!--@3depthList-->
					</ul>
					<!--@3depth-->
				</li>
				<!--@2depthList-->
			</ul>
			<!--@2depth-->
		</div>
		<!--@1depthList-->
	</div>
</div><!--@1depth-->