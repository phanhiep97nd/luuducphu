var isHideJapan = true;
/**
 * Phương thức xử lí ẩn hiện vùng trình độ tiếng nhật
 */
function showJapan() {
	if (isHideJapan) {
		document.getElementById('japan1').classList.remove("japan");
		document.getElementById('japan2').classList.remove("japan");
		document.getElementById('japan3').classList.remove("japan");
		document.getElementById('japan4').classList.remove("japan");
		isHideJapan = false;
		return;
	}
	if (!isHideJapan) {
		document.getElementById('japan1').classList.add("japan");
		document.getElementById('japan2').classList.add("japan");
		document.getElementById('japan3').classList.add("japan");
		document.getElementById('japan4').classList.add("japan");
		isHideJapan = true;
	}
}

/**
 * Phương thức xử lí khi ng dùng thay đổi selectbox tháng
 */
function changeMonth(obj, idYear, idDay) {
    var year = document.getElementById(idYear).value;
    var choose = obj.value;
	document.getElementById(idDay + 1).selected = 'selected';
    switch (choose) {
        case '4':
        case '6':
        case '9':
        case '11':
            document.getElementById(idDay + 29).style.display = 'block';
            document.getElementById(idDay + 30).style.display = 'block';
            document.getElementById(idDay + 31).style.display = 'none';
            break;
        case '2':
            if (year % 4 == 0) {
                // getDay(29);
                document.getElementById(idDay + 29).style.display = 'block';
                document.getElementById(idDay + 30).style.display = 'none';
                document.getElementById(idDay + 31).style.display = 'none';
            } else {
                //getDay(28);
                document.getElementById(idDay + 29).style.display = 'none';
                document.getElementById(idDay + 30).style.display = 'none';
                document.getElementById(idDay + 31).style.display = 'none';
            }
            break;
        default:
            document.getElementById(idDay + 29).style.display = 'block';
            document.getElementById(idDay + 30).style.display = 'block';
            document.getElementById(idDay + 31).style.display = 'block';
            break;
    }
}

/**
 * Phương thức xử lí khi ng dùng thay đổi selectbox năm
 */
function changeYear(idYear, idMonth, idDay) {
    changeMonth(document.getElementById(idMonth), idYear, idDay);
}

/**
 * Phương thức xử lí khi ng dùng click button delete
 */
function deleteUser() {
	let MSG004 = document.getElementById('MSG004').value;
	let checkOK = confirm(MSG004);
	if (checkOK == true) {
		let userId = document.getElementById('userId').value;
		let formDelete = document.createElement('form');
		formDelete.method = 'post';
		formDelete.action = "deleteUser.do";
		
		let input = document.createElement('input');
		input.type = 'hidden';
		input.name = 'id';
		input.value = userId;
		
		formDelete.appendChild(input);
		document.body.appendChild(formDelete);
		 formDelete.submit();
		 formDelete.remove();
	}
}