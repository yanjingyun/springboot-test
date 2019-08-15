/**
 * 关闭当前页面并提示信息
 * 对应controller层的Controllers.closeAndAlert(Controllers.SAVED_SUCCESSFULLY);
 */
function closeAndAlert(message) {
	if (message) {
		alert(message);
	}
	closeWindow();
}

/**
 * 关闭当前页、刷新父页面、并提示信息
 */
function closeAndRefreshParentsPage(message) {
	if (message) {
		alert(message);
	}
	if (window.opener) {
		window.opener.closeAndRefreshParentsPage();
		closeWindow();
	} else {
		refreshCurrentPage();
	}
}

function closeWindow() {// 兼容iframe中的关闭按钮
	window.top.close();
}
function refreshCurrentPage() { //刷新当前页面（这里表单不知道会不会有问题）
	window.location.reload();
}