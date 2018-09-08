package ru.rinekri.devfest2018

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ru.rinekri.devfest2018.models.RubberDuck
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object DuckMockData {
  private const val rubberDucksJson = """ [
  {
    "icon": "file:///android_asset/ducks/0f2b2bd3874567d7abeb673f6fb399d9.jpg",
    "count": 10
  },
  {
    "icon": "file:///android_asset/ducks/1a35e28ea142168c363238497226c47b.jpg",
    "count": 2
  },
  {
    "icon": "file:///android_asset/ducks/2c19fada99e69dd0ccc19d10355f9825.jpg",
    "count": 3
  },
  {
    "icon": "file:///android_asset/ducks/2e1a8c026406cbc70bb5f1aed1cf1898.jpg",
    "count": 39
  },
  {
    "icon": "file:///android_asset/ducks/3b34c1d8402a4dbbb24ac705e5552ed7.jpg",
    "count": 4
  },
  {
    "icon": "file:///android_asset/ducks/4f6251d2c61515e32f86cf83c6942b6a.png",
    "count": 7
  },
  {
    "icon": "file:///android_asset/ducks/7ab254a614375491555d23dc22a86dab.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/8cfa5423240ce4a3195c740d4837073c.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/09c6305b6f8a77a054fed8b46da701b8.jpg",
    "count": 2
  },
  {
    "icon": "file:///android_asset/ducks/9d6a480e4209a1afdc51217b93d21408.jpg",
    "count": 4
  },
  {
    "icon": "file:///android_asset/ducks/009d48020c1b383817f2e2d86e4c0226.jpg",
    "count": 6
  },
  {
    "icon": "file:///android_asset/ducks/39fb213ae51188cae842793ffe41d21c.jpg",
    "count": 7
  },
  {
    "icon": "file:///android_asset/ducks/43f57d531e2009bb180efcc35faab02c.jpg",
    "count": 9
  },
  {
    "icon": "file:///android_asset/ducks/57bb22cb0f43b4951d89282fb138852c.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/63a2b9811c73b13edfddc60787d77a20.jpg",
    "count": 2
  },
  {
    "icon": "file:///android_asset/ducks/80ba600cc232f1f852c4740935cecfbb.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/083e669441fdf693cf46fe3da8531f17.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/87cd68005124fd23fc532e4d303ee6e1.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/405a06b69bb8b4f9ffeb71a324485c38.jpg",
    "count": 3
  },
  {
    "icon": "file:///android_asset/ducks/497dfa76bd1ed9f1d5677ad391bd9b53.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/552a6ddeaab48f15f0043d5d6882aca7.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/745a9bb72a756ab9a2c3557cb636e434.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/848aec21a1bb8e04270f0178b8ef9926.jpg",
    "count": 7
  },
  {
    "icon": "file:///android_asset/ducks/877ca37469ab732e8ff3db5f99e60d4b.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/9363c6fd2cb6a7b9fc79eb5716986c5d.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/018574ab6311af613f6df86f4fb24b62.JPG",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/22662ebf32989099f1a8a9113b977390.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/421134f7c2715445609b214c847fa534.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/1041266f25debdda0dcd7de4504a4df0.jpg",
    "count": 6
  },
  {
    "icon": "file:///android_asset/ducks/3723330f14784401f3003b8392ada5d9.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/467007252b0317f8d5d932b7772b83d9.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/a3b4f854ec3a3340e4a8581975c57381.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/a3bd42c725a64a2967e94d60cb2a298c.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/a28fae20e683e9c1b95868e54e8eca44.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/ac4d8ab00e914a16250ac29fae65e342.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/ac9097029f1ce9acc3d9da52fd798b49.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/baeac702c550b5bf7107627c9b113541.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/cb8f57207bc1d8b54069635fcedd1df7.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/d297adb401401af10ecd1ce95b6c8a43.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/dbe3ff2c7928cc3d1cc62c31a7fc2848.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/efdea53d0cc15133c54508168df24ea9.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/f2adb8e1646e06095fe3dee60353a648.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/f87546e9fcdb1fbc59a34834a6711868.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/faef43cde85bbb0f612ddc489a4ffc69.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/fbbbdb0491c8f3be30e6ac177210652a.jpg",
    "count": 1
  },
  {
    "icon": "file:///android_asset/ducks/fde0b5353f1b6b18721eb88a5b21293f.jpg",
    "count": 5
  }
] """

  private val parser = Moshi.Builder().build()

  val ducks: List<RubberDuck>? by lazy {
    parser
      .adapter<List<RubberDuck>>(List::class.java.withTypes(RubberDuck::class.java))
      .fromJson(rubberDucksJson)
  }

  private fun Type.withTypes(vararg types: Type): ParameterizedType = Types.newParameterizedType(this, *types)
}