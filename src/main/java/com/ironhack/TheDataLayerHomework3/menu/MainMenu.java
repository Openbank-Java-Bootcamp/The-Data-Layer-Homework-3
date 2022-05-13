package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.utils.Input;
import com.ironhack.TheDataLayerHomework3.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ironhack.TheDataLayerHomework3.utils.Utils.clearConsole;

@Component
public class MainMenu {

    @Autowired
    SalesRepMenu salesRepMenu;

    @Autowired
    LeadMenu leadMenu;

    @Autowired
    OpportunityMenu opportunityMenu;

    @Autowired
    ReportMenu reportMenu;

    @Autowired
    Input inputAutowired;

    public void menu() {
        opening();
        credits();


        int input = 0;

        while (input != 99) {

            clearConsole();
            input = inputAutowired.promptIntWithValidation("(1) Sales Rep Menu " +
                    "\n(2) Lead Menu " +
                    "\n(3) Opportunity Status Menu " +
                    "\n(4) Report Menu " +
                    "\n\n(99) Exit", 99);

            if (input == 1) salesRepMenu.menu();
            if (input == 2) leadMenu.menu();
            if (input == 3) opportunityMenu.changeStatus();
            if (input == 4) reportMenu.menu();
        }

        System.exit(0);
    }




    public void credits(){
        clearConsole();
        Utils.printSeparator(30);
        System.out.println("  LLIJava☕FORCE");
        System.out.println("  was powered and produced with much care");
        System.out.println("  by The LLIJ Hacker Team\n");
        System.out.println("  Special thanks to our Mentors and Peers\n ");
        System.out.println("  We wish you enjoy it.");
        Utils.printSeparator(30);
        Utils.anythingToContinue();
    }

    public void opening() {
        clearConsole();
        teaCup();
        System.out.println("" +
                        "       (((,      .(((,      /((((((/  *(((((/                                    /((((((((.   .(&@@%*     .((((((*        .#&@@%(   *((((((((*         \n" +
                        "     (@@&       #@@%      ,*(@@@**. .##%@@@.   ....                   ....     /@@@&&&&&% *@@@&/*(&@@@   %@@&#%&@@@(  /@@@%/*/#@% .@@@@&&&&&.         \n" +
                        "    ,@@@*      ,@@@,        &@@(       &@@(  %&%%%&@@& .@@@. .@@@/ *@%%%%@@@, .&@@@%%%%# #@@@.    ,@@@* /@@@. .(@@@, &@@%         %@@@@@@@@.          \n" +
                        "    %@@%       &@@#        (@@&       (@@@  *&@@@@@@@/  %@@//@@%  .#@@@@@@@&  (@@&*****..@@@(     #@@@ .&@@@@@@@(   ,@@@*        *@@@*.....           \n" +
                        "   *@@@%####( /@@@#####(./(@@@#/.((//%@@@, %@@#  (@@&   *@@@@@,  *@@@. ,&@@* .@@@*       #@@@#,*#@@@#  (@@&  .&@@%   @@@@(*/(&@. %@@&(((((.           \n" +
                        "   (########, #########.(######(.&@@@&/     (@@%,###,    ###(     ,&@@/*##(  (##(          *%@@@%*    .###,    (###    /&@@@#,  *########( " +
                "");
        Utils.anythingToContinue();

    }

    public void teaCup() {

        System.out.println("" +
                        "                                                     ,((##(###%%%%%%%%%%###%%%#,                           \n" +
                        "                                                ,/(#&&&&&&&&&&&@&@@@@@@@@@@@@@@&&&###/                     \n" +
                        "                                                **@&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@&/*                    \n" +
                        "                                                (#(/(/***,%@@@@@@@@@@@@@@@@@@%****//#%%####(((##           \n" +
                        "                                                ,(#(%&/%%%%##&&#%#####%%%##&%#%#%%/&#(/*      &#(          \n" +
                        "                                                 /#(##&%%#/&&#(&%#%,&&/#%&#/&%&&%%#%%#        @##          \n" +
                        "                                                 (##%%#%%%(&&#(%%&%%&&##%%%#&%%#%###&.      @&(%           \n" +
                        "                                              ,%##%%%%%%%%%%%%%%&&%%&&%%&&%%%%%%%%%#@%/%#@@#%              \n" +
                        "                                        #%%%#%####%(&%&&&&%%&&&&&&&&&&&&&&&&&@&&@&&&&%&%%@#%(%(            \n" +
                        "                                    ((%%#(#######%&&@&@&(@%%&@%@@@@@&&&@@&@@&@&&@%&&@%%%#######%%#/        \n" +
                        "                                  .(%%#((((#######%#&@@@&@@&@@&&@&&@&%@@%@@&@@&@@@@%%%###########%##       \n" +
                        "                                  .(%%%(###((((#######@@@@@@@@@@@@@@@@@@@@@@@@@@@###(#(((((((((#%%#*       \n" +
                        "                                    #(*#((#(((((/(%@#%@@@@@@@@@@@@@@@@@@@@@@@@%&@%##((/((((/(%&(*(.        \n" +
                        "                                        %#(//%&&((#%&%&&&&&&@@@&@@&@@@&@@@@@@&&&&&%#%/#%##*/(##            \n" +
                        "                                              *&%%%#((((/(///#%#*/%%#/*&%(/*((/(/((#%%%&                   \n" +
                        "                                                                ..,,,,.. \n" +
                "");
    }

}