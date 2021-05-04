(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("frm_work");
            this.set_titletext("New Form");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("Dataset00", this);
            obj._setContents("<ColumnInfo><ConstColumn id=\"x\" type=\"STRING\" size=\"30\" value=\"10\"/><ConstColumn id=\"y\" type=\"STRING\" size=\"30\" value=\"20\"/><Column id=\"col1\" type=\"STRING\" size=\"256\"/><Column id=\"col2\" type=\"STRING\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"col1\">t</Col><Col id=\"col2\">제목</Col></Row><Row><Col id=\"col1\">a</Col><Col id=\"col2\">작성자</Col></Row><Row><Col id=\"col1\">c</Col><Col id=\"col2\">내용</Col></Row></Rows>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Button("Button00","350","30","50","38",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("검색");
            this.addChild(obj.name, obj);

            obj = new Combo("Combo00","30","30","128","38",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_innerdataset("Dataset00");
            obj.set_codecolumn("col1");
            obj.set_datacolumn("col2");
            obj.set_text("");
            this.addChild(obj.name, obj);

            obj = new Edit("Edit00","160","30","190","38",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            this.addChild(obj.name, obj);

            obj = new TextArea("TextArea00","30","70","370","220",null,null,null,null,null,null,this);
            obj.set_taborder("3");
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
        this.registerScript("frm_work.xfdl", function() {

        this.Button00_onclick = function(obj,e)
        {
        	alert("버튼을 클릭 했습니다");
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Button00.addEventHandler("onclick",this.Button00_onclick,this);
            this.Combo00.addEventHandler("onitemchanged",this.Combo00_onitemchanged,this);
        };

        this.loadIncludeScript("frm_work.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
