<!--@1depth--><nav>
			<ul class="gnb-list">
				<!--@1depthList-->
				<li class="gnb-item">
					<a href="<c:url value='{link}' />" target="{target}" class="btn btn-gnb-item" id="fs_top_menu">{name}</a>
					<!--@2depth-->
					<ul class="depth-list">
						<!--@2depthList-->
						<li class="depth-item">
							<a href="<c:url value='{link}' />" target="{target}">{name}</a>
						</li>
						<!--@2depthList-->
					</ul>
					<!--@2depth-->
				</li>
				<!--@1depthList-->
			</ul>
		</nav><!--@1depth-->