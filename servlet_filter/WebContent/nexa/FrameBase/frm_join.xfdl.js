(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("frm_join");
            this.set_titletext("frm_join");
            if (Form == this.constructor)
            {
                this._setFormPosition(1080,670);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize

            
            // UI Components Initialize
            obj = new Edit("txt_id","150","70","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_enable("true");
            obj.set_enableevent("true");
            obj.set_imemode("alpha");
            this.addChild(obj.name, obj);

            obj = new Edit("txt_pw","150","120","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_password("true");
            this.addChild(obj.name, obj);

            obj = new Edit("txt_name","150","170","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_imemode("hangul");
            this.addChild(obj.name, obj);

            obj = new Edit("txt_email","150","270","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("4");
            this.addChild(obj.name, obj);

            obj = new Button("Button00","197","360","86","34",null,null,null,null,null,null,this);
            obj.set_taborder("5");
            obj.set_text("가입");
            this.addChild(obj.name, obj);

            obj = new Static("Static00","6","7","228","46",null,null,null,null,null,null,this);
            obj.set_taborder("6");
            obj.set_text("회원가입");
            obj.set_font("normal 700 16pt/normal \"맑은고딕\"");
            obj.set_padding("0px 0px 0px 10px");
            this.addChild(obj.name, obj);

            obj = new Static("lbl_id","65","70","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("7");
            obj.set_text("아이디");
            obj.set_font("normal bold 10pt/normal \"Arial\"");
            this.addChild(obj.name, obj);

            obj = new Static("lbl_pw","65","120","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("8");
            obj.set_text("비밀번호");
            obj.set_font("normal bold 10pt/normal \"Arial\"");
            this.addChild(obj.name, obj);

            obj = new Static("lbl_name","65","170","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("9");
            obj.set_text("이름");
            obj.set_font("normal bold 10pt/normal \"Arial\"");
            this.addChild(obj.name, obj);

            obj = new Static("lbl_phone","65","220","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("10");
            obj.set_text("전화번호");
            obj.set_font("normal bold 10pt/normal \"Arial\"");
            this.addChild(obj.name, obj);

            obj = new Static("lbl_email","65","270","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("11");
            obj.set_text("이메일 주소");
            obj.set_font("normal bold 10pt/normal \"Arial\"");
            this.addChild(obj.name, obj);

            obj = new MaskEdit("txt_phone","150","220","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("3");
            obj.set_format("###-####-####");
            obj.set_type("string");
            this.addChild(obj.name, obj);

            obj = new CheckBox("CheckBox00","350","71","84","38",null,null,null,null,null,null,this);
            obj.set_taborder("12");
            obj.set_text("중복체크");
            this.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",1080,670,this,function(p){});
            obj.set_mobileorientation("landscape");
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("frm_join.xfdl", function() {
        var objApp = nexacro.getApplication();

        this.duplicateFunc = function(id, errCd, errMsg) {
        	if(this.CheckBox00.isChecked()) {
        		if(errCd == 0) {
        			alert("사용 가능한 아이디입니다.");
        		} else if(errCd == 1) {
        			alert("아이디 중복");
        		}
        	}
        };

        this.callbackFunc = function(id, errCd, errMsg) {
        	if(errCd == 0) {
        		console.log("Error Code : " + errCd);
        		console.log("Error Msg : " + errMsg);
        		this.alert("회원가입완료");
        	} else if(errCd == 1){
        		console.log("Error Code : " + errCd);
        		console.log("Error Msg : " + errMsg);
        		this.alert("회원가입실패");
        	}
        };


        this.Button00_onclick = function(obj,e)
        {
        	var id = this.txt_id.value;
        	var password = this.txt_pw.value;
        	var name = this.txt_name.value;
        	var chk = this.CheckBox00.value;
        	var frame = objApp.mainframe.VFrameSet00.HFrameSet00.WorkFrame;

        	if(id == undefined || id == " " || id == null) {
        		alert("아이디를 입력해주세요");
        		this.txt_id.setFocus();
        	} else if(chk == false) {
        		alert("아이디 중복체크 필수!");
        	} else if(password == undefined || password == "" || password == null) {
        		alert("비밀번호를 입력해주세요");
        		this.txt_pw.setFocus();
        	} else if(name == undefined || name == "" || name == null) {
        		alert("이름을 입력해주세요");
        		this.txt_name.setFocus();
        	} else {
        		frame.set_formurl("FrameBase::Form_Main.xfdl");
        	}
        	this.transaction(
        		"JoinData",
        		"http://localhost/filter/nexa/user/save",
        		"",
        		"",
        		"userid=" + this.txt_id.value
        		+ " password=" + this.txt_pw.value
        		+ " name=" + this.txt_name.value
        		+ " phone=" + this.txt_phone.value
        		+ " email=" + this.txt_email.value,
        		"callbackFunc"
        	)
        };

        this.CheckBox00_onchanged = function(obj,e)
        {
        	var id = this.txt_id.value;
        	if(id == undefined || id == " " || id == null) {
        		this.CheckBox00.isChecked(alert("아이디를 작성하고 중복확인 해주세요"));
        		this.CheckBox00.set_value(0);
        	}
        	this.duplicateFunc("test", 0, "Success");
        	this.transaction(
        		"DuplicateCheck",
        		"http://localhost/filter/nexa/user/dup",
        		"",
        		"",
        		"userid=" + this.txt_id.value,
        		"duplicateFunc"
        	)
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.txt_id.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.txt_pw.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.txt_name.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.txt_email.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.Button00.addEventHandler("onclick",this.Button00_onclick,this);
            this.Static00.addEventHandler("onclick",this.Static00_onclick,this);
            this.lbl_id.addEventHandler("onclick",this.Static01_onclick,this);
            this.lbl_pw.addEventHandler("onclick",this.Static01_onclick,this);
            this.lbl_name.addEventHandler("onclick",this.Static01_onclick,this);
            this.lbl_phone.addEventHandler("onclick",this.Static01_onclick,this);
            this.lbl_email.addEventHandler("onclick",this.Static01_onclick,this);
            this.txt_phone.addEventHandler("onchanged",this.MaskEdit00_onchanged,this);
            this.CheckBox00.addEventHandler("onchanged",this.CheckBox00_onchanged,this);
        };

        this.loadIncludeScript("frm_join.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
