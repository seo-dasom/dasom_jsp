(function()
{
	return function()
	{
		nexacro._setCSSMaps(
		{
			"menuitem" :
			{
				"parent" :
				{
					"Menu" :
					{
						"self" :
						{
							"enabled" :
							{
								"color" : nexacro.ColorObject("white"),
								"font" : nexacro.FontObject("normal 700 10pt/normal \"맑은고딕\"")
							}
						}
					}
				}
			},
			"popupmenuitem" :
			{
				"parent" :
				{
					"menupopupmenu" :
					{
						"parent" :
						{
							"Menu" :
							{
								"self" :
								{
									"enabled" :
									{
										"font" : nexacro.FontObject("normal normal 10pt/normal \"맑은고딕\""),
										"border" : nexacro.BorderObject("0px none #000000")
									}
								}
							}
						}
					}
				}
			},
			"menupopupmenu" :
			{
				"parent" :
				{
					"Menu" :
					{
						"self" :
						{
							"enabled" :
							{
								"border" : nexacro.BorderObject("0px none #000000")
							}
						}
					}
				}
			},
			"popupmenuitemtext" :
			{
				"parent" :
				{
					"popupmenuitem" :
					{
						"parent" :
						{
							"menupopupmenu" :
							{
								"parent" :
								{
									"Menu" :
									{
										"self" :
										{
											"enabled" :
											{
												"color" : nexacro.ColorObject("white")
											}
										}
									}
								}
							}
						}
					}
				}
			}
		},
		{
			"includeStatusMap" : true
		}
		);

		var imgcache = nexacro._getImageCacheMaps();
		
	};
}
)();
