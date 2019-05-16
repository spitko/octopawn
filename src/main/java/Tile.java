import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Tile extends Button {

    private static final Image pawnWhite = new Image("pawnWhite.png");
    private static final Image pawnBlack = new Image("pawnBlack.png");
    private static final Background LIGHT = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background DARK = new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background SELECTED = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));

    private final Background background;
    private final int x;
    private final int y;
    private int piece;
    private final ImageView image;


    //Mänguväljaku ruudu konstruktor koordinaatide ning ruudu pildiga
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = new ImageView();

        this.image.fitWidthProperty().bind(widthProperty());
        this.image.fitHeightProperty().bind(heightProperty());
        this.image.setPreserveRatio(true);

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        setGraphic(image);
        background = ((x + y) % 2 == 0) ? LIGHT : DARK;
        setBackground(background);

    }

    //Ruudu getterid koordinaatide jaoks ja setterid nuppude jaoks
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
        if (piece > 0) {
            image.setImage(piece == 1 ? pawnWhite : pawnBlack);
        }
        else image.setImage(null);
    }

    public void setSelected(boolean selected) {
        setBackground(selected ? SELECTED : background);
    }
}
