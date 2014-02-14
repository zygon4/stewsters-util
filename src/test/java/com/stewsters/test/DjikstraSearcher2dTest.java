package com.stewsters.test;


import com.stewsters.test.examples.ExampleMap2d;
import com.stewsters.test.examples.ExampleMover2d;
import com.stewsters.util.pathing.twoDimention.searcher.DjikstraSearcher2d;
import com.stewsters.util.pathing.twoDimention.searcher.Objective2d;
import com.stewsters.util.pathing.twoDimention.shared.FullPath2d;
import com.stewsters.util.pathing.twoDimention.shared.PathNode2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DjikstraSearcher2dTest {


    @Test
    public void testFindSomething4Way() {

        ExampleMap2d map = new ExampleMap2d(10, 10);

        DjikstraSearcher2d pathfinder = new DjikstraSearcher2d(map, 100, false);

        ExampleMover2d exampleMover2d = new ExampleMover2d(map);

        Objective2d objective2d = new Objective2d() {
            @Override
            public boolean satisfiedBy(PathNode2d current) {
                return (current.x == 8 && current.y == 8);
            }
        };

        FullPath2d fullPath2d = pathfinder.search(exampleMover2d, 1, 1, objective2d);

        for (int i = 0; i < fullPath2d.getLength(); i++) {
            System.out.println("x:" + fullPath2d.getStep(i).getX() + " y:" + fullPath2d.getStep(i).getY());
        }

        assertEquals(fullPath2d.getLength(), 15);
    }

    @Test
    public void testFindSomething8Way() {

        ExampleMap2d map = new ExampleMap2d(10, 10);

        DjikstraSearcher2d pathfinder = new DjikstraSearcher2d(map, 100, true);

        ExampleMover2d exampleMover2d = new ExampleMover2d(map);

        Objective2d objective2d = new Objective2d() {
            @Override
            public boolean satisfiedBy(PathNode2d current) {
                return (current.x == 8 && current.y == 8);
            }
        };

        FullPath2d fullPath2d = pathfinder.search(exampleMover2d, 1, 1, objective2d);

        for (int i = 0; i < fullPath2d.getLength(); i++) {
            System.out.println("x:" + fullPath2d.getStep(i).getX() + " y:" + fullPath2d.getStep(i).getY());
        }

        assertEquals(fullPath2d.getLength(), 8);
    }

}