//计算请假时长，域ID必须为 startDate, endDate, totalDays
function autoSum(startDateId, endDateId, totalDaysId) {
	var bDate = new Date();
	var eDate = new Date();
	bDate.setTime(Date.parse($("#"+startDateId).val()));
	eDate.setTime(Date.parse($("#"+endDateId).val()));
	if (bDate == NaN || eDate == NaN)
		return;
	var str = '';
	if (bDate.getFullYear() < eDate.getFullYear())
		str += eDate.getFullYear() - bDate.getFullYear() + '年';
	if (bDate.getMonth() < eDate.getMonth())
		if (bDate.getDate() < eDate.getDate())
			str += eDate.getMonth() - bDate.getMonth() + '个月 ';
	if (bDate.getDate() < eDate.getDate() || bDate.getDate() == eDate.getDate())
		str += (eDate.getDate() - bDate.getDate() + 1) + '天';
	$("#"+totalDaysId).val(str);
}