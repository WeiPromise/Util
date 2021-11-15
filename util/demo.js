{
    data: {
        entities: [
            {
                name: '祖辈',
                data: [
                    {
                        parentId: '0',
                        id: 'id:1', // 注意(id:1-1-1-2_index)
                        name: 'id:1',
                        icon: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1244784608,2184342536&fm=26&gp=0.jpg',
                        type: 'man',
                        filter: ['man', 'owner', 'focus']

                    },
                    {
                        parentId: '-',
                        id: 'id:12',
                        husbandId: 'id:1', // 如果是妻子，需要有丈夫的id
                        name: 'id:1',
                        icon: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1722506617,2322243230&fm=26&gp=0.jpg',
                        type: 'woman',
                        filter: ['woman', 'owner', 'focus']

                    },
                ]
            },

            {
                name: '父辈',
                data: [
                    {
                        parentId: 'id:1',
                        id: 'id:1-1',
                        name: 'id:1-1',
                        type: 'die',
                        filter: ['man', 'focus'],
                        icon: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1057544708,3299220796&fm=26&gp=0.jpg',

                    }, {
                        parentId: '-',
                        id: 'id:1-2',
                        husbandId: 'id:1-1',
                        name: 'id:1-2（妻）', // 注意:1-2(2要跟着父亲往后排，方便删除后代)
                        type: 'woman',
                        filter: ['woman', 'focus'],
                        icon: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2601900707,917050054&fm=26&gp=0.jpg'
                    },
                    {
                        parentId: '-',
                        id: 'id:1-3',
                        name: 'id:1-3（已故妻）',
                        husbandId: 'id:1-1',
                        type: 'die',
                        filter: ['woman', 'focus'],
                        icon: 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3201746467,2179502918&fm=26&gp=0.jpg'

                    },
                ]
            },

            {
                name: '同辈',
                data: [
                    {
                        parentId: 'id:1-1',
                        id: 'id:1-1-1',
                        name: '同辈大儿子',
                        type: 'man',
                        filter: ['man', 'owner'],
                        icon: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3594288030,4119859866&fm=26&gp=0.jpg'

                    },
                    {
                        parentId: 'id:1-1',
                        id: 'id:1-1-2',
                        name: '(本人)',
                        type: 'man',
                        filter: ['man', 'owner'],
                        icon: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3211301442,987953205&fm=26&gp=0.jpg'

                    },
                    {
                        parentId: '-',
                        id: 'id:1-1-3',
                        husbandId: 'id:1-1-2',
                        name: '同辈儿媳妇',
                        type: 'woman',
                        filter: ['woman', 'owner'],
                        icon: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2130145573,2323917832&fm=26&gp=0.jpg'

                    },
                ] },

            {
                name: '子辈',
                data: [
                    {
                        parentId: 'id:1-1-1',
                        id: 'id:1-1-1-1',
                        name: 'id:1-1-1-1',
                        type: 'man',
                        filter: ['man', 'owner'],
                        icon: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3410120239,1597870103&fm=26&gp=0.jpg'

                    },
                    {
                        parentId: 'id:1-1-1',
                        id: 'id:1-1-1-2',
                        name: 'id:1-1-1-2',
                        type: 'man',
                        filter: ['man', 'owner'],
                        icon: 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1462597699,2457940300&fm=26&gp=0.jpg'

                    },
                    {
                        parentId: 'id:1-1-2',
                        id: 'id:1-1-2-1',
                        name: 'id:1-1-2-1',
                        type: 'man',
                        filter: ['man', 'owner'],
                        icon: 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3880266746,2294673083&fm=26&gp=0.jpg'

                    }, {
                        parentId: '-',
                        id: 'id:1-1-2-2',
                        husbandId: 'id:1-1-2-1',
                        name: 'id:1-1-2-2',
                        type: 'woman',
                        filter: ['woman', 'owner'],
                        icon: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2130145573,2323917832&fm=26&gp=0.jpg'

                    }
                ]
            }

        ],
            relations: [
            // 祖辈
            {

                id: 'edges-11',
                source: 'id:1',
                target: 'id:12',
                relation: 'spouse_relation',
                separate: false

            },
            // 祖父辈（竖线）
            {

                id: 'edges-21',
                source: 'id:1',
                target: 'id:1-1',
                relation: '',
                separate: false

            },
            // 父辈
            {
                id: 'edges-31',
                source: 'id:1-1',
                target: 'id:1-2',
                text: '配偶',
                relation: 'spouse_relation', // 配偶的type格式化要用到
                separate: false // 是否离异

            },
            {
                id: 'edges-32',
                source: 'id:1-1',
                target: 'id:1-3',
                relation: 'spouse_relation',
                separate: true // 是否离异

            },
            // 父同辈（竖线）
            {
                id: 'edges-41',
                source: 'id:1-1',
                target: 'id:1-1-1',
                relation: '',
                separate: false // 是否离异

            },
            {
                id: 'edges-42',
                source: 'id:1-1',
                target: 'id:1-1-2',
                relation: '',
                separate: false // 是否离异

            },
            {
                id: 'edges-43',
                source: 'id:1-1',
                target: 'id:1-1-4',
                relation: '',
                separate: false // 是否离异

            },
            // 同辈
            {
                id: 'edges-51',
                source: 'id:1-1-2',
                target: 'id:1-1-3',
                text: '配偶',
                relation: 'spouse_relation',
                separate: false // 是否离异

            },
            // 同子辈（竖线）
            {
                id: 'edges-61',
                source: 'id:1-1-1',
                target: 'id:1-1-1-1',
                relation: '',
                separate: false // 是否离异

            },
            {
                id: 'edges-62',
                source: 'id:1-1-1',
                target: 'id:1-1-1-2',
                relation: '',
                separate: false // 是否离异
            },
            {
                id: 'edges-63',
                source: 'id:1-1-2',
                target: 'id:1-1-2-1',
                relation: '',
                separate: false // 是否离异

            },
            // 子辈
            {
                id: 'edges-64',
                source: 'id:1-1-2-1',
                target: 'id:1-1-2-2',
                relation: 'spouse_relation',
                text: '配偶',
                separate: false // 是否离异

            },

        ]
    }
}