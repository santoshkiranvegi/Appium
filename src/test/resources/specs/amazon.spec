

@objects
    navLogo       id     nav-logo
    GiftCard      css    #nav-xshop > a:nth-child(4)
    



= Content =
       @on desktop
        navLogo:
            height 20 to 100 px
            inside welcome-block ~ 68 px top

        GiftCard:
	        width ~ 192px
	        text is "Gift Cards"