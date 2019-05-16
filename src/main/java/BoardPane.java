import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.List;

public class BoardPane extends GridPane {

    private Tile[][] tiles;
    private Tile selected = null;
    private SimpleIntegerProperty player;
    private StringProperty status;

    public BoardPane(int size) {
        super();
        player = new SimpleIntegerProperty(1);
        status = new SimpleStringProperty();
        StringConverter<Number> statusConverter = new StringConverter<>() {
            @Override
            public String toString(Number object) {
                switch (object.intValue()) {
                    case 1:
                        return "White to move";
                    case 2:
                        return "Black to move";
                    case -1:
                        return "White wins";
                    case -2:
                        return "Black wins";
                    default:
                        return "";
                }
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        };

        //Staatuse seosatamine mängijatega
        status.bindBidirectional(player, statusConverter);

        //Mänguväljaku loomine, suurusega vastavalt mängija poolt sisestatud valiku järgi
        tiles = new Tile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Tile tile = new Tile(x, y);
                tile.prefWidthProperty().bind(widthProperty().divide(size));
                tile.prefHeightProperty().bind(heightProperty().divide(size));
                tile.setOnAction(event -> onSelect(tile));
                tiles[y][x] = tile;
                add(tile, x, y);
            }
        }
        reset();
    }

    //Mänguväljaku resettimine algseisu
    public void reset() {
        Arrays.stream(tiles[0]).forEach(t -> t.setPiece(1));
        for (int i = 1; i < tiles.length - 1; i++) {
            Arrays.stream(tiles[tiles.length - 1]).forEach(t -> t.setPiece(0));
        }
        Arrays.stream(tiles[tiles.length - 1]).forEach(t -> t.setPiece(2));
    }

    //Mänguväljaku ruudu valimine sündmus
    private void onSelect(Tile tile) {
        if (player.get() < 0) {
            return;
        }
        if (selected == null) {
            selected = tile;
            tile.setSelected(true);
            return;
        }

        Move move = new Move(selected.getX(), selected.getY(), tile.getX(), tile.getY());
        List<Move> moves = Move.findMoves(tiles, player.get());
        if (moves.contains(move)) {
            player.set(3 - player.get());
            makeMove(selected, tile);
        }
        selected.setSelected(false);
        selected = null;

    }

    //Nupu liikumine mänguväljakul
    private void makeMove(Tile previous, Tile next) {
        next.setPiece(previous.getPiece());
        previous.setPiece(0);
        checkWin();
    }

    //Võidutingimuste kontroll
    private void checkWin() {
        if (Arrays.stream(tiles[0]).anyMatch(i -> i.getPiece() == 2)
                || Arrays.stream(tiles[tiles.length - 1]).anyMatch(i -> i.getPiece() == 1)
                || Move.findMoves(tiles, player.getValue()).isEmpty()) {
            player.set(-3 - player.negate().get());
        }
    }

    //Staatuse tagastamine
    public StringProperty getStatus() {
        return status;
    }
}
