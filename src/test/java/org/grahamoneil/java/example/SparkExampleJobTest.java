package org.grahamoneil.java.example;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SparkExampleJobTest {

    private static StructType expectedStructType;

    @BeforeAll
    public static void runOnceBeforeClass() {
        List<StructField> listOfStructField = new ArrayList<>();
        listOfStructField.add(DataTypes.createStructField("emp_id", DataTypes.IntegerType, true));
        listOfStructField.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        listOfStructField.add(DataTypes.createStructField("emp_dept_id", DataTypes.IntegerType, true));
        listOfStructField.add(DataTypes.createStructField("dept_id", DataTypes.IntegerType, true));
        listOfStructField.add(DataTypes.createStructField("dept_name", DataTypes.StringType, true));
        expectedStructType = DataTypes.createStructType(listOfStructField);
    }

    @Test
    /*
      Runs the SparkExampleJob innerJoinDataFrames method and verifies the DataFrame is correct.
     */
    void sparkExampleJobInnerJoin() {
        SparkExampleJob exampleJob = new SparkExampleJob();
        exampleJob.innerJoinDataFrames();
        //exampleJob.final_df.show();
        Assertions.assertEquals(expectedStructType, exampleJob.final_df.schema());
        Assertions.assertEquals(4, exampleJob.final_df.count());
    }

    @Test
    /*
      Runs the SparkExampleJob innerJoinDataFrames method and verifies results.
     */
    void sparkExampleJobLeftJoin() {
        SparkExampleJob exampleJob = new SparkExampleJob();
        exampleJob.leftJoinDataFrames();
        //exampleJob.final_df.show();
        Assertions.assertEquals(expectedStructType, exampleJob.final_df.schema());
        Assertions.assertEquals(1, exampleJob.final_df.filter("dept_id is null").count());
    }

    @Test
    /*
      Runs the SparkExampleJob rightJoinDataFrames method and verifies results.
     */
    void sparkExampleJobRightJoin() {
        SparkExampleJob exampleJob = new SparkExampleJob();
        exampleJob.rightJoinDataFrames();
        //exampleJob.final_df.show();
        Assertions.assertEquals(expectedStructType, exampleJob.final_df.schema());
        Assertions.assertEquals(1, exampleJob.final_df.filter("emp_id is null").count());
    }
}
