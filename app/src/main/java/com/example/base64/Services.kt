package com.example.base64

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class Services {

    val baseurl="https://project.codunite.in/roomRent/api/"
    public final val loginurl="parentlogin"

    var dashbord_menu: Load_DashBord_Menu? = null
    fun get_dashbord_menu(): Load_DashBord_Menu? {
        if (dashbord_menu == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            dashbord_menu = retrofit.create(Load_DashBord_Menu::class.java)
        }
        return dashbord_menu
    }

/*    landlord_name
    landlord_mobile
    address
    property_type
    property
    property_area
    city
    floor_no
    furnished_type
    monthly_rent_amount
    security_amount
    maintainence_charge*/

    interface Load_DashBord_Menu {
        @FormUrlEncoded
        @POST("saveDetail")
        fun getLandingPageReport(
            @Field("landlord_name") school_id: String?,
            @Field("landlord_mobile") student_id: String?,
            @Field("address") reason: String?,
            @Field("property_type") description: String?,
            @Field("property") start_date: String?,
            @Field("property_area") end_date: String?,
            @Field("city") city: String?,
            @Field("floor_no") floor_no: String?,
            @Field("furnished_type") furnished_type: String?,
            @Field("monthly_rent_amount") monthly_rent_amount: String?,
            @Field("security_amount") security_amount: String?,
            @Field("maintainence_charge") maintainence_charge: String?)
                : Call<Datum>?
    }
}