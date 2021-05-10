(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("Form_Work");
            this.set_titletext("Form_Work");
            if (Form == this.constructor)
            {
                this._setFormPosition(1080,670);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize

            
            // UI Components Initialize
            obj = new Static("Static00","0","0","264","60",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("메인화면");
            obj.set_font("normal 700 16pt/normal \"맑은고딕\"");
            obj.set_padding("0px 0px 0px 10px");
            this.addChild(obj.name, obj);

            obj = new Grid("Grid00","12","90","400","200",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_binddataset("dataset00");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell text=\"번호\"/><Cell col=\"1\" text=\"이름\"/><Cell col=\"2\" text=\"나이\"/></Band><Band id=\"body\"><Cell text=\"bind:id\"/><Cell col=\"1\" text=\"bind:name\"/><Cell col=\"2\" text=\"bind:age\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);

            obj = new Button("Button00","352","64","60","20",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_text("검색");
            this.addChild(obj.name, obj);

            obj = new Edit("Edit00","222","64","130","20",null,null,null,null,null,null,this);
            obj.set_taborder("3");
            this.addChild(obj.name, obj);

            obj = new Button("Button01","13","64","60","20",null,null,null,null,null,null,this);
            obj.set_taborder("4");
            obj.set_text("추가");
            this.addChild(obj.name, obj);

            obj = new Button("Button02","77","64","60","20",null,null,null,null,null,null,this);
            obj.set_taborder("5");
            obj.set_text("삭제");
            this.addChild(obj.name, obj);

            obj = new Button("Button03","352","300","60","20",null,null,null,null,null,null,this);
            obj.set_taborder("6");
            obj.set_text("저장");
            this.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","Desktop_screen",1080,670,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("Form_Work.xfdl", function() {
        this.callbackFunc = function(id, errCd, errMsg) {
        	if(errCd < 0) {
        		console.log("Error Code : " + errCd);
        		console.log("Error Msg : " + errMsg);
        	} else {
        		console.log(errMsg);
        	}
        };

        this.Form_Work_onload = function(obj,e)
        {
        	this.transaction(
        		"initData",
        		"http://localhost/filter/nexa/init",
        		"",
        		"dataset00=serverData",
        		"data=",
        		"callbackFunc"
        	)
        	/*
        	this.transaction(
        		"트랜젝션ID",
        		"요청주소",
        		"서버 송신 데이터셋",
        		"서버 수신 데이터셋",
        		"트랜젝션간 전달할 변수",
        		"통신 완료 후 호출할 함수"
        	)
        	*/
        };

        this.Button00_onclick = function(obj,e)
        {
        	this.transaction(
        		"initData",
        		"http://localhost/filter/nexa/init",
        		"",
        		"dataset00=serverData",
        		"data=" + this.Edit00.value,
        		"callbackFunc"
        	)
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.addEventHandler("onload",this.Form_Work_onload,this);
            this.Static00.addEventHandler("onclick",this.Static00_onclick,this);
            this.Button00.addEventHandler("onclick",this.Button00_onclick,this);
            this.Edit00.addEventHandler("onchanged",this.Edit00_onchanged,this);
        };

        this.loadIncludeScript("Form_Work.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
