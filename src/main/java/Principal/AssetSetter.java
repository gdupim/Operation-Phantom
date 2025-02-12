package Principal;

public class AssetSetter {
    Janela j;

    public AssetSetter(Janela j) {
        this.j = j;
    }

    public void setItem() {
        j.item[0] = new Itens.ITEM_PeDeCabra();
        j.item[0].worldX = 2 * j.tileSize;
        j.item[0].worldY = 2 * j.tileSize;

        j.item[1] = new Itens.ITEM_PeDeCabra();
        j.item[1].worldX = 2 * j.tileSize;
        j.item[1].worldY = 5 * j.tileSize;
    }
}