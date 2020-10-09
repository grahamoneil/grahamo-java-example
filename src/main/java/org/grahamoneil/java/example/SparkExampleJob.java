package org.grahamoneil.java.example;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

public class SparkExampleJob {

    public Dataset<Row> final_df = null;
    public SparkSession spark = null;

    public SparkExampleJob() {
        this.spark = SparkSession.builder()
                .appName("SparkExampleJob")
                .master("local")
                .getOrCreate();
    }

    private Dataset<Row> getDepartmentDataFrame() {
        List<Row> list = new ArrayList<Row>();
        list.add(RowFactory.create(5, "HR"));
        list.add(RowFactory.create(6, "Sales"));
        list.add(RowFactory.create(10, "Engineering"));

        List<StructField> listOfStructField = new ArrayList<StructField>();
        listOfStructField.add(DataTypes.createStructField("dept_id", DataTypes.IntegerType, true));
        listOfStructField.add(DataTypes.createStructField("dept_name", DataTypes.StringType, true));
        StructType structType = DataTypes.createStructType(listOfStructField);
        Dataset<Row> data = this.spark.createDataFrame(list, structType);
        return data;
    }

    private Dataset<Row> getEmployeeDataFrame() {
        List<Row> list = new ArrayList<Row>();
        list.add(RowFactory.create(1, "Graham", 10));
        list.add(RowFactory.create(2, "Bill", 5));
        list.add(RowFactory.create(3, "John", 10));
        list.add(RowFactory.create(4, "Jane", 6));

        List<StructField> listOfStructField = new ArrayList<StructField>();
        listOfStructField.add(DataTypes.createStructField("emp_id", DataTypes.IntegerType, true));
        listOfStructField.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        listOfStructField.add(DataTypes.createStructField("emp_dept_id", DataTypes.IntegerType, true));
        StructType structType = DataTypes.createStructType(listOfStructField);
        Dataset<Row> data = this.spark.createDataFrame(list, structType);
        return data;
    }

    public void leftJoinDataFrames() {
        Dataset<Row> emp_df = this.getEmployeeDataFrame();
        Dataset<Row> dept_df = this.getDepartmentDataFrame();
        //emp_df.show();
        //dept_df.show();
        this.final_df = emp_df.join(dept_df, emp_df.col("emp_dept_id").equalTo(dept_df.col("dept_id")));
        //this.final_df.show();
    }
}
