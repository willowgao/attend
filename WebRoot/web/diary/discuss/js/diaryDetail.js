 	jq(function() {
 		getContentData();
	});
	
	 
	//查询列表数据
	function getContentData() { 
		jq.ajaxSettings.async = false; 
		var params =  jq('#diaryDailyForm').serialize();
		jq.ajax( {
			url : programName + '/diary/diaryDiscuss!getContent.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				jq.each(data,function(idx,item){  
					 //输出 
					 if(idx=='diaryDaily.content'){
						jq('#content').textbox('setValue',item);
					 }  
					 if(idx=='diaryDaily.nextcontent'){
							jq('#nextcontent').textbox('setValue',item);
					 } 
					  
				});
			}
		});
	}
	
	 
	
	 
	