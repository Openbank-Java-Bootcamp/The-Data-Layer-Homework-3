package com.ironhack.TheDataLayerHomework3.menu;

import com.ironhack.TheDataLayerHomework3.utils.Colors;
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
            input = inputAutowired.promptIntWithValidation(
                    Colors.RESET+ "\n(1)" +Colors.GREEN + " Sales Rep Menu " +
                    Colors.RESET+ "\n(2)" +Colors.BLUE +  " Lead Menu " +
                    Colors.RESET+ "\n(3)" +Colors.PURPLE +" Opportunity Status Menu " +
                    Colors.RESET+ "\n(4)" +Colors.CYAN +" Report Menu " +
                    Colors.RESET+ "\n\n(99)" +Colors.RED +" Exit" + Colors.RESET, 99 );

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
        System.out.println(Colors.CYAN_BOLD + "  LLIJava ☕ FORCE"+ Colors.RESET);
        System.out.println("  was powered and produced with much care");
        System.out.println("  by The LLIJ Hacker Team\n");
        System.out.println(Colors.GREEN_BACKGROUND + Colors.BLACK_BOLD + "  Special thanks to our Mentors and Peers "+ Colors.RESET);
        System.out.println("\n   We wish you enjoy it.");
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