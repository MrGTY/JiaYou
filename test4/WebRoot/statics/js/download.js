function downimg(src) {
	if (confirm("你确定要下载吗？")) {
		var obj=$(src);
		var $a = $("<a></a>").attr("href",obj.attr("srcimg")).attr("download",obj.attr("downimg"));
		$a[0].click();
		$a.remove();
	}
};
