package com.example.videoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.videoapp.adapters.RecAdapter
import com.example.videoapp.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: RecAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        val videoList = mutableListOf(
            VideoModel(
                "repair iphone",
                "https://v16-webapp.tiktok.com/42617c998cca83567d5afc9de4fba891/6290eab7/video/tos/alisg/tos-alisg-pve-0037c001/a9b1fe546a7e468592ebe292b0d808c8/?a=1988&ch=0&cr=0&dr=0&lr=tiktok_m&cd=0%7C0%7C1%7C0&cv=1&br=2670&bt=1335&btag=80000&cs=0&ds=3&ft=eXd.6HmiMyq8ZGFsSwe2Nzbnml7Gb&mime_type=video_mp4&qs=0&rc=ZDxnZGg5PGZpNmYzN2Q6O0BpajZucjM6Zm5vOzMzODczNEAyXjY1NmBgXzIxMC8uL2FgYSNkMXE2cjQwbWtgLS1kMS1zcw%3D%3D&l=202205270913420102452421071C044307",
                "1"
            ),
//            VideoModel(
//                "python",
//                "https://rr3---sn-4g5e6ns7.googlevideo.com/videoplayback?expire=1653579256&ei=mEmPYq3dFMHG1wKRqrqICg&ip=86.106.74.119&id=o-AKX20Y8wNPc7GlGQCVD2nlCtUDadBeI8iDjXqZeL1kzI&itag=18&source=youtube&requiressl=yes&spc=4ocVCyimHoZ5hvjwhB7T15HyXIiv&vprv=1&mime=video%2Fmp4&ns=GgNJ9VxlP_tx5uM7BC0iR50G&gir=yes&clen=331813&ratebypass=yes&dur=8.576&lmt=1653291890922790&fexp=24001373,24007246&c=WEB&txp=6310224&n=spH30OAJQGDxyA&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgd__OSp21ZdgNx8oHOYoCQSNKxLRmMo9pONAPTwdw_esCIB4sG-IbSvgNJZEzlEMwI1CYEDvwU_UqDXBx2IICqE6z&redirect_counter=1&cm2rm=sn-5hnes67l&req_id=989f3511ad3fa3ee&cms_redirect=yes&cmsv=e&mh=ba&mip=194.61.91.178&mm=34&mn=sn-4g5e6ns7&ms=ltu&mt=1653557562&mv=m&mvi=3&pl=22&lsparams=mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRgIhAJqj5pa2PIa-d7dd-tkODyhTpG24KgIZ5MxfX2l7h3DyAiEAp9IhebDnOu99hR-5E-aqOFDzLPsuBTVxtf9FbtcwuC4%3D"
//            ),
//            VideoModel(
//                "iphone",
//                "https://rr6---sn-nv47ln7s.googlevideo.com/videoplayback?expire=1653579340&ei=7EmPYvGdDqrfkATIyay4CQ&ip=209.107.204.89&id=o-ALYSxx7d6FSu-mBd8wKbTrNdZZRxZU_w1Jchn2ri1U8t&itag=18&source=youtube&requiressl=yes&spc=4ocVCxgK46LCMKc80XiM24r6L4Vx&vprv=1&mime=video%2Fmp4&ns=EUHxnKbUlY2G6P6ZnArCS9sG&gir=yes&clen=1052173&ratebypass=yes&dur=14.186&lmt=1646472898422933&fexp=24001373,24007246&c=WEB&txp=5430434&n=Y8tzmbn-OaviMQ&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgYkEgXOr6OXshBgXLbL26siRqhPKm5K2lH4h8L_8qiaMCIALOEQnTCOvrBzg23st8gD0cPEWSrEpmNH-GPaB8nTit&rm=sn-hp5e7e7z&req_id=4d2fdf8ba3b6a3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-5g2poxuaaj-x8ol7e&cms_redirect=yes&cmsv=e&mh=V5&mip=194.61.91.178&mm=29&mn=sn-nv47ln7s&ms=rdu&mt=1653557585&mv=m&mvi=6&pl=22&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgRrNhSHwO_gQbJYqx9bEc_CMgMsTHSsFwgbGMM7J18fgCIGpC10rubItxJiNl9qiRawDHErK5KLnx5IbLgVXDP_2Z"
//            ),
//            VideoModel(
//                "iphone 12 pro max",
//                "https://rr3---sn-nv47lnsk.googlevideo.com/videoplayback?expire=1653579390&ei=HkqPYrm3BOqlir4PxNKc6AI&ip=198.181.163.53&id=o-AEtsJ780nYA7JL-wZXSwe42pAIz4ORrTL7kV3SJZn4BH&itag=18&source=youtube&requiressl=yes&spc=4ocVC-J8uYHCl4lr_CudDwaC1epu&vprv=1&mime=video%2Fmp4&ns=bQ64CY9liyrDeoh9LaiM_fsG&gir=yes&clen=1270399&ratebypass=yes&dur=12.181&lmt=1646441311640454&fexp=24001373,24007246&c=WEB&txp=6310224&n=1L4vDM6QXu27pQ&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgLZdBvU_siBrYRyeha6UuvGnQ2KOaAZ_CjBIR121KKO0CIBr3YEDF3Irv9jNTfasD5gZvvVbMbfaH3hai01-p1udY&rm=sn-p5qee67l&req_id=ebd11a14b43ba3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-5g2poxuaaj-x8ol7l&cms_redirect=yes&cmsv=e&mh=pU&mip=194.61.91.178&mm=29&mn=sn-nv47lnsk&ms=rdu&mt=1653557585&mv=m&mvi=3&pl=22&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgOxcTrwalFqxEusNrztz8xzJAmMiqTZEb5yjsbM_yJA0CIGawi_H5nKAp6B634DlTFOn6elkots6k0Hu_IK5CR9v9"
//            ),
//            VideoModel(
//                "nokia 1200",
//                "https://rr5---sn-nv47ln7z.googlevideo.com/videoplayback?expire=1653579474&ei=cUqPYqX9OczG1wL20Yz4Cg&ip=91.90.123.53&id=o-AJE4Z8ePzHTuV3w4_Au3d2IcgXDGf00V4iaFkwCJedZb&itag=18&source=youtube&requiressl=yes&spc=4ocVC4jEEWUDzAEfqDncS02WvYMI&vprv=1&mime=video%2Fmp4&ns=Ot9j1PTKrxAoyoc9cTd5NWYG&gir=yes&clen=757689&ratebypass=yes&dur=11.626&lmt=1645241386774666&fexp=24001373,24007246&c=WEB&txp=6310224&n=yd99Ze06xwUCWQ&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhANJZfnIS-DTP-CDpGFbsv4ZSVSOa3gQPZ4vNp9RQOkplAiEA2XGH008euM8TL9rdNunokYaXo1-MZLoZwBVIL-XXc_0%3D&rm=sn-5hness7s&req_id=8daa3a39f07ba3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-5g2poxuaaj-x8os7l&cms_redirect=yes&cmsv=e&mh=p5&mip=194.61.91.178&mm=29&mn=sn-nv47ln7z&ms=rdu&mt=1653557585&mv=m&mvi=5&pl=22&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgBJo6QRBP7jBxApWHLTQV0QVvOCMzkhs95vHjIEySvl4CICEAGhLWfVFNgK3sulwSE18VIqMybnb7A8qRC4hZ7CyU"
//            )
        )

        db.collection("videos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    videoList += (VideoModel(
                        document.get("videoId").toString(),
                        document.get("videoURL").toString(),
                        document.get("videoType").toString()
                    ))
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }

        adapter = context?.let { RecAdapter(videoList, it) }!!
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter
        val snapHelper = LinearSnapHelper() // Or PagerSnapHelper
        snapHelper.attachToRecyclerView(binding.recView)
    }
}
