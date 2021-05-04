(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("frm_base");
            this.set_titletext("New Form");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize

            
            // UI Components Initialize
            obj = new Button("btn0","58","22","190","58",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("버튼");
            this.addChild(obj.name, obj);

            obj = new Grid("Grid00","58","96","308","150",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_autofittype("col");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"48\"/><Column size=\"48\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell text=\"NAME\"/><Cell col=\"1\" text=\"AGE\"/></Band><Band id=\"body\"><Cell text=\"홍길동\"/><Cell col=\"1\" text=\"30\"/><Cell row=\"1\" text=\"김마리\"/><Cell row=\"1\" col=\"1\" text=\"28\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",1280,720,this,function(p){});
            obj.set_mobileorientation("landscape");
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("frm_base.xfdl", function() {

        this.btn0_onclick = function(obj,e)
        {
        	alert("버튼 클릭!");
        };

        this.btn0_onmouseenter = function(obj,e)
        {
        	alert("마우스 이벤트");
        };

        // this : 현재 작업중인 form을 의미
        // btn0_onclick : btn0은 컴포넌트 ID
        // btn0_onmouseenter : onclick, onmouseenter 이벤트 명
        // obj:nexacro.Button : obj 는 매개변수 이름으로 이 변수에는
        //                      컴포넌트 객체 정보가 있다.
        // e:nexacro.ClickEventInfo : e 는 매개변수 이름으로 이 변수에는
        //                            발생된 이벤트 정보가 있다.
        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.btn0.addEventHandler("onclick",this.btn0_onclick,this);
            this.btn0.addEventHandler("onmouseenter",this.btn0_onmouseenter,this);
        };

        this.loadIncludeScript("frm_base.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
