package HomeWork3;

import java.io.File;
import java.net.URL;

/**
 * Class: GameCollect
 * @author Mike Deiters
 * @version 1.0
 * August 26, 2015
 * ITEC 3150-01
 *
 * Description: Determine what OS the application is being ran on
 *
 * Purpose: Locate the Text File and Image Files
 */
public class GameCollect {

    private File gameFile;
    private String unknownGame;
    private String planetside2;
    private String leagueOfLegends;
    private String blackOpsIII;
    private String theCrew;
    private String minecraft;
    private String monumentValley;
    private String destinyTakenKingXbox;
    private String destinyTakenKingPS4;
    private String metalGearSolid;
    private String dontGetFired;
    private String crossyRoad;
    private String portal2;

    /**
     * Constructor: GameCollect
     * Description: Determines what file system is in use
     */
    public GameCollect() throws FileMissingException {

        String OS = System.getProperty("os.name");

        if ( OS.startsWith("Windows") ) {

            winImg();
        }
        else {

            unixImg();
        }
    }

    /**
     * Method: getGameFile
     * @return gameFile File location of the games
     */
    public File getGameFile() {

        return gameFile;
    }

    /**
     * Method: findGame
     * @param game Game that is requesting cover art
     * @return String of the image location
     * Description: Using the game id determines which game cover art is being requested
     */
    public String findGame( Game game ) {

        if ( game == null ) { // Checks to see if a game did get passed in

            // If no game got pased in then returns the unknownGame image

            return unknownGame;
        }
        else {

            switch ( game.getGameId() ) { // Returns the game image string based on the game id

                case 1001: // Planetside 2

                    return planetside2;

                case 1002: // League of Legends

                    return leagueOfLegends;

                case 1003: // Portal 2

                    return portal2;

                case 2001: // Black Ops III

                    return blackOpsIII;

                case 2002: // The Crew

                    return theCrew;

                case 2003: // Destiny Taken King PS$

                    return destinyTakenKingPS4;

                case 2004: // Desting Taken King Xbox

                    return destinyTakenKingXbox;

                case 2005: // Metal Gear Solid

                    return metalGearSolid;

                case 3001: // Minecraft

                    return minecraft;

                case 3002: // Monument Valley

                    return monumentValley;

                case 3003: // Don't get Fired

                    return dontGetFired;

                case 3004: // Crossy Road

                    return crossyRoad;

                default: // Unknown Game

                    return unknownGame;
            }
        }
    }

    /**
     * Method: winImg
     * @return void
     * Description: File and Image locations in the Windows file system
     */
    public void winImg() throws FileMissingException {

        try {

            URL path = GameCollect.class.getResource("Txt/gameList.txt");
            this.gameFile = new File(path.getFile());
            this.unknownGame = getClass().getResource("Images\\Unknown-Game.png").toExternalForm();
            this.planetside2 = getClass().getResource("Images\\planetside2.jpg").toExternalForm();
            this.leagueOfLegends = getClass().getResource("Images\\leagueOfLegends.jpg").toExternalForm();
            this.blackOpsIII = getClass().getResource("Images\\blackOpsIII.jpg").toExternalForm();
            this.theCrew = getClass().getResource("Images\\theCrew.jpg").toExternalForm();
            this.minecraft = getClass().getResource("Images\\minecraft.jpg").toExternalForm();
            this.monumentValley = getClass().getResource("Images\\monumentValley.png").toExternalForm();
            this.destinyTakenKingXbox = getClass().getResource("Images\\destiny-taken-king-xbox.png").toExternalForm();
            this.destinyTakenKingPS4 = getClass().getResource("Images\\destiny-taken-king-ps4.jpg").toExternalForm();
            this.metalGearSolid = getClass().getResource("Images\\metal-gear-solid.jpg").toExternalForm();
            this.portal2 = getClass().getResource("Images\\portal-2.jpg").toExternalForm();
            this.dontGetFired = getClass().getResource("Images\\dont-get-fired.jpg").toExternalForm();
            this.crossyRoad = getClass().getResource("Images\\crossy-road.jpg").toExternalForm();
        }
        catch ( NullPointerException npe ) {

            throw new FileMissingException("I can find one of the files you are looking for.");
        }
    }

    /**
     * Method: unixImg
     * @return void
     * Description: File and Image locations in the unix file systems
     */
    public void unixImg() throws FileMissingException {

        try {

            URL path = GameCollect.class.getResource("Txt/gameList.txt");
            this.gameFile = new File(path.getFile());
            this.unknownGame = getClass().getResource("Images/Unknown-Game.png").toExternalForm();
            this.planetside2 = getClass().getResource("Images/planetside2.jpg").toExternalForm();
            this.leagueOfLegends = getClass().getResource("Images/leagueOfLegends.jpg").toExternalForm();
            this.blackOpsIII = getClass().getResource("Images/blackOpsIII.jpg").toExternalForm();
            this.theCrew = getClass().getResource("Images/theCrew.jpg").toExternalForm();
            this.minecraft = getClass().getResource("Images/minecraft.jpg").toExternalForm();
            this.monumentValley = getClass().getResource("Images/monumentValley.png").toExternalForm();
            this.destinyTakenKingXbox = getClass().getResource("Images/destiny-taken-king-xbox.png").toExternalForm();
            this.destinyTakenKingPS4 = getClass().getResource("Images/destiny-taken-king-ps4.jpg").toExternalForm();
            this.metalGearSolid = getClass().getResource("Images/metal-gear-solid.jpg").toExternalForm();
            this.portal2 = getClass().getResource("Images/portal-2.jpg").toExternalForm();
            this.dontGetFired = getClass().getResource("Images/dont-get-fired.jpg").toExternalForm();
            this.crossyRoad = getClass().getResource("Images/crossy-road.jpg").toExternalForm();
        }
        catch ( NullPointerException npe ) {

            throw new FileMissingException("I can find one of the files you are looking for.");
        }
    }
}
