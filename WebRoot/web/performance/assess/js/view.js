   jq(function() {
		jq('#dlg').dialog('close');
		uploadFile();
	});
    
	 
	var query = function(){
		jq('#dlg').dialog('open');
		
		jq.ajaxSettings.async = false; 
		jq.getJSON(programName + '/assess/documentView!viewDocument.action', function(re_datas) {
			 if(re_datas!=''){
				jq('#diary').attr('src',programName+'/web/html/'+re_datas);
			 }
		});  
	}
	
    var uploadFile = function(){
    	jq("#uploader").pluploadQueue({
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : programName+'/web/common/plupload/js/plupload.flash.swf',// Flash环境路径设置
			silverlight_xap_url :programName+'/web/common/plupload/js/plupload.silverlight.xap',//silverlight环境路径设置
			url : programName+'/assess/documentView!upload.action',//上传文件路径
			max_file_size : '3gb',//100b, 10kb, 10mb, 1gb
			chunk_size : '1mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			// resize : { width : 320, height : 240, quality : 90 },
			// 指定要浏览的文件类型
			filters : [ {
				title : 'Image files',
				extensions : 'jpg,gif,png'
			}, {
				title : 'Zip files',
				extensions : 'zip,7z'
			} , {
				title : 'Office files',
				extensions : 'doc,docx,xls,xlsx,ppt,pptx,txt'
			}],
			init : {
				FileUploaded : function(up, file, info) {//文件上传完毕触发
					console.info(up);
					console.info(file);
					console.info(info);
					var response = jq.parseJSON(info.response);
					if (response.status) {
						jq('#docUpload').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
						jq('#docUpload').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
					}
				}
			}
		});
    	
    }
	 
    var saveFile = function(){
		var rows = null;
		var uploader = jq('#uploader').pluploadQueue();
		if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
			uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
				if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
					var params =  jq('#docUpload').serialize();
					jq.ajax( {
						url : programName + '/assess/documentView!save.action',
						type : 'post',
						data : params,
						dataType : 'json',
						success : function(data) {
							rows = data;
						}
					});
					return rows;
				}
			});
			uploader.start();
		} else {
			jq.messager.alert('提示','请选择至少一个文件进行上传！');
		}
		return false;
		
		
		
		
		
		
    }
	
	