(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("Form_Main");
            this.set_titletext("Form_Main");
            if (Form == this.constructor)
            {
                this._setFormPosition(960,656);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize

            
            // UI Components Initialize


            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",960,656,this,function(p){});
            obj.set_mobileorientation("landscape");
            this.addLayout(obj.name, obj);
            
            // BindItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script

        
        // Regist UI Components Event
        this.on_initEvent = function()
        {

        };

        this.loadIncludeScript("Form_Main.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
