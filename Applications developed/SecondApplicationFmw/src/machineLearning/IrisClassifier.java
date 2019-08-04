package machineLearning;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.classifiers.Classifier;

import java.io.File;
import java.util.*;

public class IrisClassifier extends Algorithm {

    private String _modelFile;

    private Classifier _classifier;

    public IrisClassifier() {
        _modelFile = (new File("src/resources/iris-random-tree.model")).getAbsolutePath();
    }

    private void initialize() throws Exception {
        _classifier = (Classifier) SerializationHelper.read(_modelFile);
    }

    @Override
    public double predict(double[] features) {
        Attribute sepalLengthAttribute = new Attribute("sepallength");
        Attribute sepalWidthAttribute = new Attribute("sepalwidth");
        Attribute petalLengthAttribut = new Attribute("petallength");
        Attribute petalWidthAttribut = new Attribute("petalwidth");

        List<String> classes = new ArrayList<String>() {
            {
                add("Iris setosa");
                add("Iris versicolor");
                add("Iris virginica");
            }
        };

        ArrayList<Attribute> attributues = new ArrayList<Attribute>(2) {
            {
                add(sepalLengthAttribute);
                add(sepalWidthAttribute);
                add(petalLengthAttribut);
                add(petalWidthAttribut);
                Attribute attributeClass = new Attribute("@@class@@", classes);
                add(attributeClass);
            }
        };

        Instances unpredicted = new Instances("TestInstances", attributues, 1);
        unpredicted.setClassIndex(unpredicted.numAttributes() - 1);

        DenseInstance instance = new DenseInstance(unpredicted.numAttributes()) {
            {
                setValue(sepalLengthAttribute, features[0]);
                setValue(sepalWidthAttribute, features[1]);
                setValue(petalLengthAttribut, features[2]);
                setValue(petalWidthAttribut, features[3]);
            }
        };

        instance.setDataset(unpredicted);

        try {
            return _classifier.classifyInstance(instance);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean fit(double[][] features, double[] labels) {
        return false;
    }

    @Override
    public void save() {
    }

    @Override
    public void read() {
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
