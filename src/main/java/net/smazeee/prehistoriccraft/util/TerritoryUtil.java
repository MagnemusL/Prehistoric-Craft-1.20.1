package net.smazeee.prehistoriccraft.util;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class TerritoryUtil {
    List<List<BlockPos>> posLists = new ArrayList<>();
    List<Integer> idList = new ArrayList<>();

    public TerritoryUtil(List<BlockPos> posList, int id) {
        this.posLists.add(posList);
        this.idList.add(id);
    }

    public TerritoryUtil get(int id) {
        for (int i = 0; i < idList.size(); i++) {
            if (idList.get(i).equals(id)) {
                return new TerritoryUtil(posLists.get(i), idList.get(i));
            }
        }
        return new TerritoryUtil(new ArrayList<>(), -1);
    }

    public void add(List<BlockPos> posList, int id) {
        posLists.add(posList);
        idList.add(id);
    }

    public void addToList(List<BlockPos> posList, int id) {
        for (int i = 0; i < idList.size(); i++) {
            if (idList.get(i).equals(id)) {
                for (int y = 0; i < posList.size(); i ++) {
                    List<List<BlockPos>> currentList = new ArrayList<>();
                    currentList.add(posLists.get(i));
                    posLists.remove(i);
                    currentList.get(0).add(posList.get(y));
                    posLists.add(i, currentList.get(0));
                }
            }
        }
    }
}
