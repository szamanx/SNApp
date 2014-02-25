package snapp.domain;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: daniel
 * Date: 23/02/14
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
public class Wall {
    private List<Message> wall = new ArrayList<>();

    public List<Message> getWall() {
        return wall;
    }

    public void setWall(List<Message> wall) {
        this.wall = wall;
    }
}
