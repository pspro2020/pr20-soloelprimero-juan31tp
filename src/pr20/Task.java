package pr20;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Place> {

    int matrixSegment[];
    int row, lookFor;

    public Task(int i, int[] matrix, int lookFor) {
        this.row=i;
        this.matrixSegment=matrix;
        this.lookFor=lookFor;
    }

    @Override
    public Place call() throws Exception {
        Place position = null;

        TimeUnit.MILLISECONDS.sleep((long)(Math.random()*999)+500);
        for (int i=0; i < matrixSegment.length; i ++){
            if (matrixSegment[i] == lookFor){
                position = new Place(row+1, i+1);
            }
        }

        if (position != null) {
            return position;
        } else {
            throw new RuntimeException();
        }
    }
}
