package Principal;



public class AssetSetter {
    Janela j;

    public AssetSetter(Janela j) {
        this.j = j;
    }

    public void setItem() {
        j.item[0] = new Itens.ITEM_PeDeCabra(j);
        j.item[0].worldX = 2 * j.tileSize;
        j.item[0].worldY = 2 * j.tileSize;

        j.item[1] = new Itens.ITEM_PeDeCabra(j);
        j.item[1].worldX = 2 * j.tileSize;
        j.item[1].worldY = 5 * j.tileSize;

        j.item[2] = new Itens.CON_cafe(j);
        j.item[2].worldX = 5 * j.tileSize;
        j.item[2].worldY = 2 * j.tileSize;
    }
    public void setNPC() {

        j.npc[0] = new Entidades.NPC_temp(j);
        j.npc[0].worldX = 5 * j.tileSize;
        j.npc[0].worldY = 5 * j.tileSize;

    }
    public void setInimigo() {

        j.inimigo[0] = new Entidades.INI_sold_cacetete(j);
        j.inimigo[0].worldX = 5 * j.tileSize;
        j.inimigo[0].worldY = 6 * j.tileSize;

        j.inimigo[1] = new Entidades.INI_sold_cacetete(j);
        j.inimigo[1].worldX = 5 * j.tileSize;
        j.inimigo[1].worldY = 7 * j.tileSize;
        
    }
}