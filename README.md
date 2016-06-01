# Unmapped
Import of https://code.google.com/archive/p/unmapped/

> NOTICE: This project is cancelled and is being rewritten with a better UI at  https://github.com/ssfsx17/unmappedtwo - it also really badly needed a re-architecting.

------------------------------------------------------------------------------------------------
You gather around the comfortable fire at the tavern, in a remote town where adventure is to be found.

"Then we are agreed?" asks Roswitha, a priestess who has decided to go out of the sheltered cloister and into the big wide world. "We will swear this pact of loyalty, to achieve justice and glory for all?" Everyone nods.

You all swear a pact as blood-brethren, to seek good and avoid evil, and to bring everlasting honor and glory to your names.

You look around at each other. One of you, Zeni, is a battle-scarred veteran who has seen the full horrors of what humans are willing to do to each other. "Now taht we have a goal," Zeni remarks wryly, "how shall we achieve it?"

"Let's visit the market and the guilds in search of better equipment and more training," suggests Sugihara. She has studied and scribed for her whole life, and loathes risk. Above all, she fears that someone will accuse her of making a mistake. "I've also heard that the churches and monasteries may take on students. Once we find some teachers, we can just stay here and work at various things until the money runs low!"

Everyone nods, even Rory the Rascal. "A wise plan," remarks Rory, "but why not take action now? Lowlives infest the back streets here at night. We can gain repute, experience, and a bit of plunder by eliminating these scum. Let us also listen to rumors and visit the villages to learn about opportunities to advance ourselves."

There is an unhappy look on Sugihara's face. "Don't we need fine armor and good weapons? Maybe the sale of one or two valuable items would give us the funds to help everyone here. I am loathe to venture into the dangerous countryside without good equipment."

After discussing these issues, you decide to...

* ...look at the downloads
* [...ask the tavernkeeper about his younger days of adventuring](http://en.wikipedia.org/wiki/Darklands_(video_game))
* [...consult the scholars who are also experts in dwarven craftsmanship](http://www.bay12forums.com/smf/index.php?topic=121701.0)
* [...converse with other adventurers who have tried many kinds of quests](http://forums.tigsource.com/index.php?topic=30968.0)
* [...enjoy the ambient music at this tavern](https://soundcloud.com/ssfsx17)
* [...look towards the future of the realm](https://github.com/ssfsx17/unmappedtwo)
* [...search for more choices](http://www.google.com)
* [...discuss unrelated topics to pass the time](http://www.reddit.com)

-----------------------------------------------------------------------------------------------------------

Unmapped is a Darklands-like "role-playing" game with a minimal pure-text interface, powered by the Lanterna GUI library ( http://code.google.com/p/lanterna/'>http://code.google.com/p/lanterna/ ). The combat is based on The Bard's Tale series. The game has no visual map whatsoever, hence its name.

It requires Java to run. If you can run PCGen, MegaMek, MekHQ, etc., then you should hopefully be able to run this.

## Current things to see:

Party of up to 6 characters
Go to the Guild Street or the Garment District for shopping
Go to the temple at the Central Plaza
Go to the slums and pick a fight
Go outside town and explore the countryside, visit a village, or march towards a raubritter's (robber-knight's) tower
Holy Book system, roughly analagous to the Roman Catholic saints systems of Darklands
Source code available under the GPLv2 on Google Code - if there is enough demand then I may also put the source on GitHub

## New in 0.0.8:

Random encounters in the countryside - currently, only two are implemented
Main menu is accessible from outside town
There is now a benefit to going to villages that are not evil
Running away from a fight will remove shock damage ("stumbled", "staggered", "totally rocked", etc). Also, shock damage is also always removed while camping/resting, even if the character does not choose to rest.
Bugfixed savegames so that they will keep track of character damage

## Planned:

Training non-combat skills - the really good training will require Norton Coins
More encounters of many kinds
More possible encounters when picking a fight in the slums
Ranged weapons with reload time - crossbows and firearms
Other encounters & battles that show off the engine's flexibility, e.g. skeletons who are highly resistant to cutting & piercing damage
More shops and stuff to go in the shops
Improvements to chargen
Alchemy (and Cooking / Food system?)
Ask for music submissions
Implement save files as JSON rather than XML, for maximum resume padding
Finish up the quests that are started in villages

## Shelved for later:

Character descriptions - This is not gonna work without a lot of custom coding, because Lanterna UI doesn't have text input boxes that handle multiple lines.

## FAQ

### How to help?

Suggestions and feedback!

What takes most of the time is thinking of encounters and all the different things that should be able to happen in them.


### Java is infamous for security issues

This only applies to the plugins that run Java in a web browser. This affects nVidia's hardware detector, Facebook's advanced photo uploader, and many corporate applets. However, desktop and server Java are still fine. In fact, if desktop and server-side Java had a security issue, the whole corporate world and the entire country of India would melt, but that is another story for another day.

And, if you heard that Java was bad, wait'll you see Flash and Silverlight. Generally, web browser plugins that provide "rich functionality" are inherent security issues.

If you're really concerned, then use Linux + this: http://openjdk.java.net/'>http://openjdk.java.net/ or this: http://www.ibm.com/developerworks/java/jdk/index.html'>http://www.ibm.com/developerworks/java/jdk/index.html - and turn off the Java web browser plugin


### Exception in thread "main" java.lang.UnsupportedClassVersionError

Try updating to the latest version of Java.


#### This game is difficult!

Look in the source code for a file named "spoilers.txt" to see some of the secrets of the game. Or you can just hack your save file.
