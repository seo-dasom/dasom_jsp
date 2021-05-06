(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("Form_Top");
            this.set_titletext("Form_Top");
            this.set_scrolltype("none");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,50);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("Dataset00", this);
            obj._setContents("<ColumnInfo><Column id=\"id\" type=\"STRING\" size=\"256\"/><Column id=\"name\" type=\"STRING\" size=\"256\"/><Column id=\"level\" type=\"STRING\" size=\"256\"/><Column id=\"url\" type=\"STRING\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"id\">1</Col><Col id=\"name\">메뉴1</Col><Col id=\"level\">0</Col></Row><Row><Col id=\"id\">2</Col><Col id=\"name\">서브메뉴1</Col><Col id=\"level\">1</Col><Col id=\"url\">FrameBase::frm_work1.xfdl</Col></Row><Row><Col id=\"id\">3</Col><Col id=\"name\">메뉴2</Col><Col id=\"level\">0</Col></Row><Row><Col id=\"id\">4</Col><Col id=\"name\">서브메뉴1</Col><Col id=\"level\">1</Col><Col id=\"url\">FrameBase::frm_work2.xfdl</Col></Row><Row><Col id=\"id\">5</Col><Col id=\"name\">서브메뉴2</Col><Col id=\"level\">1</Col><Col id=\"url\">FrameBase::frm_work3.xfdl</Col></Row></Rows>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Static("Static00","6","2","228","46",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("네비게이션");
            obj.set_font("normal 700 16pt/normal \"맑은고딕\"");
            obj.set_padding("0px 0px 0px 10px");
            this.addChild(obj.name, obj);

            obj = new Menu("Menu00","160","0","1090","50",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_background("black");
            obj.set_innerdataset("Dataset00");
            obj.set_captioncolumn("name");
            obj.set_idcolumn("id");
            obj.set_levelcolumn("level");
            obj.set_color("cornflowerblue");
            obj.set_font("normal normal 16pt/normal \"맑은고딕\"");
            this.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","Desktop_screen",1280,50,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("Form_Top.xfdl", function() {
        var objApp = nexacro.getApplication();

        this.Menu00_onmenuclick = function(obj,e)
        {
        	var sid = e.id;
        	var url = this.objects.Dataset00.lookup("id", sid, "url");

        	var frame = objApp.mainframe.VFrameSet00.HFrameSet00.WorkFrame;
        	frame.set_formurl(url);
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Menu00.addEventHandler("onmenuclick",this.Menu00_onmenuclick,this);
        };

        this.loadIncludeScript("Form_Top.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
