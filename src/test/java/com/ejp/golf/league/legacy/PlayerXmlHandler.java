//package com.ejp.golf.league.legacy;
//
//import com.ejp.golf.league.domain.Golfer;
//import org.xml.sax.Attributes;
//import org.xml.sax.helpers.DefaultHandler;
//
//public class PlayerXmlHandler extends DefaultHandler {
//
//    private Golfer golfer;
//
//    @Override
//    public void startDocument() {
//        System.out.println("Start Document");
//    }
//
//    @Override
//    public void endDocument() {
//        System.out.println("End Document");
//    }
//
//    @Override
//    public void startElement(
//            String uri,
//            String localName,
//            String qName,
//            Attributes attributes) {
//        System.out.printf("Start Element : %s%n", qName);
//
//        if( qName.equals("Players")){
//            golfer = new Golfer();
//        }else if( qName.equals("FirstName")){
////            golfer.setFirstName(attributes.);
//        }
//
//
//    }
//
//    @Override
//    public void endElement(String uri,
//                           String localName,
//                           String qName) {
//
//        System.out.printf("End Element : %s%n", qName);
//    }
//}
