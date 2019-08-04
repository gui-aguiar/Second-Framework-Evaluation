package br.com.irisflower.business;

import br.com.irisflower.model.ParamsModel;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.classifiers.Classifier;

import java.util.*;

public class IrisClassifier {

    private String _modelFile;

    private Classifier _classifier;

    public IrisClassifier(String modelFile) {
        _modelFile = modelFile;
    }

    public void initialize() throws Exception {
        _classifier = (Classifier) SerializationHelper.read(_modelFile);
    }

    public String predict(ParamsModel params) throws Exception {
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

        Instances unpredicted = new Instances("XXXTestInstances", attributues, 1);
        unpredicted.setClassIndex(unpredicted.numAttributes() - 1);

        DenseInstance instance = new DenseInstance(unpredicted.numAttributes()) {
            {
                setValue(sepalLengthAttribute, params.getSepalLength());
                setValue(sepalWidthAttribute, params.getSepalWidth());
                setValue(petalLengthAttribut, params.getPetalLength());
                setValue(petalWidthAttribut, params.getPetalWidth());
            }
        };

        instance.setDataset(unpredicted);

        double result = _classifier.classifyInstance(instance);

        return classes.get(new Double(result).intValue());
    }
}
