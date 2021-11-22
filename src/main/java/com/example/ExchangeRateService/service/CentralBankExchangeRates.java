package com.example.ExchangeRateService.service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.ExchangeRateService.domain.Currency;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class CentralBankExchangeRates {

    private static final String CENTRAL_BANK_RATES_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    private CentralBankExchangeRates() {
    }

    public static List<Currency> currencyFileXmlParser() {
        var doc = getXMLDocument();
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Cube");
        return getCurrencies(nList);
    }

    public static Document getXMLDocument(){
        Document doc = null;
        var dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            var url = new URL(CENTRAL_BANK_RATES_URL);
            doc = db.parse(url.openStream());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return doc;

    }
    private static List<Currency> getCurrencies(NodeList nList) {
        List<Currency> currencyArrayList = new ArrayList<>();
        for (var temp = 0; temp < nList.getLength(); temp++) {
            var nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                var eElement = (Element) nNode;
                if (!((Element) nNode).getAttribute("currency").isEmpty()) {
                    var currency = new Currency(eElement.getAttribute("currency"), Double.parseDouble(eElement.getAttribute("rate")));
                    currencyArrayList.add(currency);
                }
            }
        }
        return currencyArrayList;
    }
}
