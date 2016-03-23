    jq(function() {

		jq('#deptid').combobox({    
			url:getDeptByOrg(),
		    valueField:'id',    
		    textField:'text'   
		}); 
		
    });
/**
 * 页面分页查询
 * @return
 */
var query = function(){
	var starttime = jq('#starttime').datebox('getValue');
	var endtime = jq('#endtime').datebox('getValue');
    jq('#dg').treegrid({
        url:  programName + '/query/queryWorks!queryWorks.action?starttime='+starttime+'&endtime='+endtime
    });
}
	
