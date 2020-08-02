package sample

import sample.Model.ToyMerchant
import java.text.SimpleDateFormat
import java.util.*

class Util{
    companion object{
        fun printToyObject(toyMerchant: ToyMerchant):String{
            val sb =StringBuilder()
            if(toyMerchant.customMessage!=null){
                sb.append(toyMerchant.customMessage)
            }
            if(toyMerchant.name!=null){
                sb.append("Toy Name : ${toyMerchant.name}")
            }
            if(toyMerchant.code!=null){
                sb.append(" ,Toy Code : ${toyMerchant.code}")
            }
            if(toyMerchant.description!=null){
                sb.append(" ,Toy Description : ${toyMerchant.description}")
            }
            if(toyMerchant.price!=0){
                sb.append(" ,Toy Price : ${toyMerchant.price}")
            }
            if(toyMerchant.dom !=null){
                sb.append(" ,Toy Manufacture Date : ${toyMerchant.dom}")
            }
            if(toyMerchant.toyManufacturer !=null){
                if(toyMerchant.toyManufacturer.companyName!=null){
                    sb.append(" ,Toy Manufacture Company : ${toyMerchant.toyManufacturer.companyName}")
                }
                if(toyMerchant.toyManufacturer.country!=null){
                    sb.append(" ,Toy Manufacture Country : ${toyMerchant.toyManufacturer.country}")

                }
                if(toyMerchant.toyManufacturer.zipCode!=null){
                    sb.append(" ,Toy Manufacture ZipCode : +${toyMerchant.toyManufacturer.zipCode}")
                }
                if(toyMerchant.toyManufacturer.streetAddress!=null){
                    sb.append(" ,Toy Manufacture Address : P.O. BOX ${toyMerchant.toyManufacturer.streetAddress}")
                }

            }
            return sb.toString()
        }
        fun getCurrentDateTime():String{
            val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val date = Date();
            return dateFormat.format(date)

        }


    }
}