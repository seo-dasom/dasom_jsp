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
            obj = new Edit("Edit00","150","70","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_enable("true");
            obj.set_enableevent("true");
            obj.set_imemode("alpha");
            this.addChild(obj.name, obj);

            obj = new Edit("Edit01","150","120","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_password("true");
            this.addChild(obj.name, obj);

            obj = new Edit("Edit02","150","170","180","40",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_imemode("hangul");
            this.addChild(obj.name, obj);

            obj = new Edit("Edit04","150","270","180","40",null,null,null,null,null,null,this);
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

            obj = new Static("Static01","65","70","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("7");
            obj.set_text("아이디");
            this.addChild(obj.name, obj);

            obj = new Static("Static01_00","65","120","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("8");
            obj.set_text("비밀번호");
            this.addChild(obj.name, obj);

            obj = new Static("Static01_01","65","170","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("9");
            obj.set_text("이름");
            this.addChild(obj.name, obj);

            obj = new Static("Static01_02","65","220","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("10");
            obj.set_text("전화번호");
            this.addChild(obj.name, obj);

            obj = new Static("Static01_03","65","270","70","36",null,null,null,null,null,null,this);
            obj.set_taborder("11");
            obj.set_text("이메일 주소");
            this.addChild(obj.name, obj);

            obj = new MaskEdit("MaskEdit03","150","220","180","40",null,null,null,null,null,null,this);
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
        		if(errCd == 2) {
        			alert("아이디중복");
        		} else {
        			alert("사용가능한 아이디입니다.");
        		}
        	}
        };

        this.callbackFunc = function(id, errCd, errMsg) {
        	if(errCd < 0) {
        		console.log("Error Code : " + errCd);
        		console.log("Error Msg : " + errMsg);
        		console.log(errMsg);
        	} else {
        		console.log("Error Code : " + errCd);
        		console.log(errMsg);
        	}
        };


        this.Button00_onclick = function(obj,e)
        {
        	var id = this.Edit00.value;
        	var password = this.Edit01.value;
        	var name = this.Edit02.value;
        	var chk = this.CheckBox00.value;
        	var frame = objApp.mainframe.VFrameSet00.HFrameSet00.WorkFrame;

        	if(id == undefined || id == " " || id == null) {
        		alert("아이디를 입력해주세요");
        		this.Edit00.setFocus();
        	} else if(chk == false) {
        		alert("아이디 중복체크 필수!");
        	} else if(password == undefined || password == "" || password == null) {
        		alert("비밀번호를 입력해주세요");
        		this.Edit01.setFocus();
        	} else if(name == undefined || name == "" || name == null) {
        		alert("이름을 입력해주세요");
        		this.Edit02.setFocus();
        	} else {
        		frame.set_formurl("FrameBase::Form_Main.xfdl");
        	}
        	this.transaction(
        		"saveData",
        		"http://localhost/filter/nexa/user/save",
        		"data=dataset01:U",
        		"dataset01=serverData",
        		"",
        		"callbackFunc"
        	)
        };

        this.CheckBox00_onchanged = function(obj,e)
        {
        	var id = this.Edit00.value;
        	if(id == undefined || id == " " || id == null) {
        		this.CheckBox00.isChecked(alert("아이디를 작성하고 중복확인 해주세요"));
        		this.CheckBox00.set_value(0);
        	}
        	this.transaction(
        		"DuplicateCheck",
        		"http://localhost/filter/nexa/user/dup",
        		"",
        		"",
        		"userid=" + this.Edit00.value,
        		"duplicateFunc"
        	)
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Edit00.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.Edit01.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.Edit02.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.Edit04.addEventHandler("onchanged",this.Edit00_onchanged,this);
            this.Button00.addEventHandler("onclick",this.Button00_onclick,this);
            this.Static00.addEventHandler("onclick",this.Static00_onclick,this);
            this.Static01.addEventHandler("onclick",this.Static01_onclick,this);
            this.Static01_00.addEventHandler("onclick",this.Static01_onclick,this);
            this.Static01_01.addEventHandler("onclick",this.Static01_onclick,this);
            this.Static01_02.addEventHandler("onclick",this.Static01_onclick,this);
            this.Static01_03.addEventHandler("onclick",this.Static01_onclick,this);
            this.MaskEdit03.addEventHandler("onchanged",this.MaskEdit00_onchanged,this);
            this.CheckBox00.addEventHandler("onchanged",this.CheckBox00_onchanged,this);
        };

        this.loadIncludeScript("frm_join.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
