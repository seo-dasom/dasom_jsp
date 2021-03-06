(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("Form_Left");
            this.set_titletext("Form_Left");
            this.set_scrolltype("vertical");
            if (Form == this.constructor)
            {
                this._setFormPosition(320,656);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("Dataset00", this);
            obj._setContents("<ColumnInfo><Column id=\"name\" type=\"STRING\" size=\"256\"/><Column id=\"depth\" type=\"STRING\" size=\"256\"/><Column id=\"url\" type=\"STRING\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"name\">KH 소개</Col><Col id=\"depth\">0</Col></Row><Row><Col id=\"name\">연혁</Col><Col id=\"depth\">1</Col></Row><Row><Col id=\"name\">오시는 길</Col><Col id=\"depth\">1</Col></Row><Row><Col id=\"name\">과정 소개</Col><Col id=\"depth\">0</Col></Row><Row><Col id=\"name\">자바 학부</Col><Col id=\"depth\">1</Col></Row><Row><Col id=\"name\">보안 학부</Col><Col id=\"depth\">1</Col><Col id=\"url\">[Undefined]</Col></Row><Row><Col id=\"name\">KH 소식</Col><Col id=\"depth\">0</Col><Col id=\"url\">FrameBase::frm_work1.xfdl</Col></Row><Row><Col id=\"name\">인재 채용</Col><Col id=\"depth\">0</Col><Col id=\"url\">FrameBase::frm_work2.xfdl</Col></Row><Row><Col id=\"name\">회원가입</Col><Col id=\"depth\">0</Col><Col id=\"url\">FrameBase::frm_join.xfdl</Col></Row></Rows>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Static("Static00","0","0","200","50",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("왼쪽 메뉴");
            obj.set_padding("0px 0px 0px 10px");
            obj.set_font("normal bold 16pt/normal \"맑은고딕\"");
            this.addChild(obj.name, obj);

            obj = new Grid("Grid00","0","50","319","470",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_binddataset("Dataset00");
            obj.set_autofittype("col");
            obj.set_treeusecheckbox("false");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"80\"/></Columns><Rows><Row size=\"24\"/></Rows><Band id=\"body\"><Cell text=\"bind:name\" displaytype=\"treeitemcontrol\" edittype=\"tree\" treelevel=\"bind:depth\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);

            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","Desktop_screen",320,656,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("Form_Left.xfdl", function() {
        var objApp = nexacro.getApplication();

        this.Grid00_oncelldblclick = function(obj,e)
        {
        	var rid = e.row;
        	var url = this.objects.Dataset00.getColumn(rid, "url");
        	if(url != undefined) {
        		var frame = objApp.mainframe.VFrameSet00.HFrameSet00.WorkFrame;
        		frame.set_formurl(url);
        	}
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Grid00.addEventHandler("oncelldblclick",this.Grid00_oncelldblclick,this);
        };

        this.loadIncludeScript("Form_Left.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
